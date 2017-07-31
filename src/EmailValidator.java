/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simeon
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
    
    private Pattern pattern;
	private Matcher matcher;

	private static final String EMAIL_PATTERN =
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public EmailValidator() {
		pattern = Pattern.compile(EMAIL_PATTERN);
	}

	/**
	 * Validate emailExpr with regular expression
	 *
	 * @param emailExpr
	 *            emailExpr for validation
	 * @return true valid emailExpr, false invalid emailExpr
	 */
	public boolean validate(final String emailExpr) {

		matcher = pattern.matcher(emailExpr);
		return matcher.matches();

	}
    
}
