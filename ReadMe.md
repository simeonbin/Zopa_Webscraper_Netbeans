
                                             SIMEON BINIATIDIS
											 31/07/2017
											 

I provide a Java and Selenium based script with use of Netbeans 8.2 IDE. Subsequent releases could be done in IntelliJ and gradle.

The script Tests 5 Zopa "Clients" with only Email, FirstName, LastName, DOB.
Subsequent Releases should contain the rest, e.g. Title, Loan Reason, Address history, Financial Details etc

I only validate Emails with regex but no verification. There are various ways to do email-validation and verification
using either JavaMail API, or Apache Commons Email validator, and others.

I used the following RegEx for Emails
Regex: Emails
^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$

To validate UK Postal Codes the one below can be used
Regex: UK Postal Codes
^[A-Z]{1,2}[0-9R][0-9A-Z]? [0-9][ABD-HJLNP-UW-Z]{2}$

To validate UK Phone Land Lines  the one below 
RegEx: UK Phone Land Lines 
^((\(?0\d{4}\)?\s?\d{3}\s?\d{3})|(\(?0\d{3}\)?\s?\d{3}\s?\d{4})|(\(?0\d{2}\)?\s?\d{4}\s?\d{4}))(\s?\#(\d{4}|\d{3}))?$ 

To validate UK Mobile Phone Numbers  the one below 
RegEx: UK Mobile Phone Numbers
^(\+44\s?7\d{3}|\(?07\d{3}\)?)\s?\d{3}\s?\d{3}$ 





