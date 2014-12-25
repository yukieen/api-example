package web.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.thymeleaf.util.StringUtils;

/***
 * 
 * @see {@link http://docs.aws.amazon.com/ja_jp/ElasticLoadBalancing/latest/
 *      DeveloperGuide /TerminologyandKeyConcepts.html#x-forwarded-headers}
 */
public class ELBHttpServletRequestWrapper extends HttpServletRequestWrapper {
    private final Log logger = LogFactory.getLog(ELBHttpServletRequestWrapper.class);

    /** クライアント→ELB間のプロトコル **/
    private final String protocol;
    /** クライアント→ELB間のポート **/
    private final int port;
    /** クライアントのアドレス **/
    private final String remoteAddr;

    public ELBHttpServletRequestWrapper(HttpServletRequest request) {
	super(request);

	logger.debug("requestURL(before):" + getRequestURL());
	
	String hfor = getHeader("X-FORWARDED-FOR");
	if (hfor != null && hfor != "") {
	    String[] split = hfor.split(",");
	    remoteAddr = split[0];
	} else {
	    remoteAddr = null;
	}

	String hport = getHeader("X-FORWARDED-PORT");
	if (hport != null && hport != "") {
	    port = Integer.valueOf(hport);
	} else {
	    port = -1;
	}

	String hprotocol = getHeader("X-FORWARDED-PROTO");
	if (hprotocol != null && hprotocol != "") {
	    protocol = StringUtils.equals("http", hprotocol) ? "https" : hprotocol;
	} else {
	    protocol = null;
	}

	logger.debug("requestURL(after):" + getRequestURL());
    }

    @Override
    public int getServerPort() {
	return port != -1 ? port : super.getServerPort();
    }

    @Override
    public String getScheme() {
	return protocol != null ? protocol : super.getScheme();
    }

    @Override
    public String getRemoteAddr() {
	return isELBRequest() ? remoteAddr : super.getRemoteAddr();
    }

    @Override
    public StringBuffer getRequestURL() {
	int port = getServerPort();
	String protocol = getScheme();
	return isELBRequest() ? new StringBuffer(protocol)
		.append("://")
		.append(getServerName())
		.append((port == 443 || port == 80) ? "" : ":"
			+ String.valueOf(port)).append(getRequestURI()) : super
		.getRequestURL();
    }

    @Override
    public boolean isSecure() {
	return protocol != null ? "https".equals(protocol) : super.isSecure();
    }

    protected boolean isELBRequest() {
	return remoteAddr != null;
    }

}
