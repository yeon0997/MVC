package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControlInterface {
	public void requestPro(
			HttpServletRequest request, HttpServletResponse response) 
			throws Throwable;
}
