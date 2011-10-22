package com.edu.service;


/**
 * An exception that is thrown by classes wanting to trap unique 
 * constraint violations.  This is used to wrap Spring's 
 * DataIntegrityViolationException so it's checked in the web layer.
 *
 * @author Reid
 */
public class TutorialNotFoundException extends Exception {
    private static final long serialVersionUID = 4050482305178810173L;

    /**
     * Constructor for UserExistsException.
     *
     * @param message exception message
     */
    public TutorialNotFoundException(final String message) {
        super(message);
    }
}
