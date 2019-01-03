package threadPool.simpleThrealPool;

public class DiscardException extends RuntimeException {

	private static final long serialVersionUID = 6677345202315357796L;

	DiscardException(String message) {
		super(message);
	}
}
