package org.iditex.ecommerce.model.repositories;

public abstract class RepositoryException extends RuntimeException {

    protected RepositoryException() {
    }

    protected RepositoryException(String message) {
        super(message);
    }

    protected RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    protected RepositoryException(Throwable cause) {
        super(cause);
    }
}
