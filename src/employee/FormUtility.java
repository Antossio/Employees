package employee;
import java.io.*;
import java.net.*;
import java.util.*;
public class FormUtility
{
	private final static String encode = "UTF-8";

	private static Random random = new Random();

	public static String getBoundary()
	{
		return "---------------------------" + randomString() +
				randomString()
				+ randomString();
	}

	public static Map<String, String> parse(String form)
	{
		Map<String, String> result = new HashMap<String, String>();
		StringTokenizer tok = new StringTokenizer(form, "&");
		while (tok.hasMoreTokens())
		{

			String str = tok.nextToken();
			StringTokenizer tok2 = new StringTokenizer(str, "=");
			if (!tok2.hasMoreTokens())
			{
				continue;
			}
			String left = tok2.nextToken();
			if (!tok2.hasMoreTokens())
			{
				left = encode(left);
				result.put(left, null);
				continue;
			}
			String right = tok2.nextToken();
			right = encode(right);
			result.put(left, right);
		}
		return result;
	}

	private static String encode(String str)
	{
		try
		{
			return URLEncoder.encode(str, encode);
		} catch (UnsupportedEncodingException e)
		{
			return str;
		}
	}

	protected static String randomString()

	{
		return Long.toString(random.nextLong(), 36);
	}

	private String boundary;

	private OutputStream os;

	private boolean first;

	public FormUtility(OutputStream os2, String boundary2) {
		this.os = os2;
		this.boundary = boundary2;
	}

	public void add(String name, File file) throws IOException
	{
		if (this.boundary != null)
		{
			boundary();
			writeName(name);
			write("; filename=\"");
			write(file.getName());
			write("\"");
			newline();
			write("Content-Type: ");
			String type =
					URLConnection.guessContentTypeFromName(file.getName());
			if (type == null)
			{
				type = "application/octet-stream";
			}
			writeln(type);
			newline();
			byte[] buf = new byte[8192];
			int nread;
			InputStream in = new FileInputStream(file);
			while ((nread = in.read(buf, 0, buf.length)) >= 0)
			{
				this.os.write(buf, 0, nread);
			}
			newline();
		}
	}

	public void add(String name, String value) throws IOException
	{

		if (this.boundary != null)
		{
			boundary();
			writeName(name);
			newline();
			newline();
			writeln(value);
		} else
		{
			if (!this.first)
			{
				write("&");
			}
			write(encode(name));
			write("=");
			write(encode(value));
		}
		this.first = false;
	}

	public void complete() throws IOException
	{
		if (this.boundary != null)
		{
			boundary();
			writeln("--");
			this.os.flush();
		}
	}

	private void boundary() throws IOException
	{
		write("--");
		write(this.boundary);

	}

	private void newline() throws IOException
	{
		write("\r\n");
	}

	private void write(String str) throws IOException
	{
		this.os.write(str.getBytes());
	}

	private void writeName(String name) throws IOException
	{
		newline();
		write("Content-Disposition: form-data; name=\"");
		write(name);
		write("\"");
	}

	protected void writeln(String str) throws IOException
	{
		write(str);
		newline();
	}
}