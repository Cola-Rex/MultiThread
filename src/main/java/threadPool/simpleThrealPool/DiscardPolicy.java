package threadPool.simpleThrealPool;

/**
 * 拒绝策略接口
 */
public interface DiscardPolicy {

	void discard() throws DiscardException;
}
