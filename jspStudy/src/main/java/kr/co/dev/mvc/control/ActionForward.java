package kr.co.dev.mvc.control;

public class ActionForward {
	private String url;
	private boolean redirect;
	
	public ActionForward() {}

	public ActionForward(String url, boolean redirect) {
		super();
		this.url = url;
		this.redirect = redirect;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public void setRedirect(boolean redirect) {
		this.redirect = redirect;
	}

	@Override
	public String toString() {
		return "ActionForward [url=" + url + ", redirect=" + redirect + "]";
	}

}
