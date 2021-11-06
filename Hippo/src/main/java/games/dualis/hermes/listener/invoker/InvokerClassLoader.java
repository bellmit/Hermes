package games.dualis.hermes.listener.invoker;

/**
 * @author Hippo
 *
 * <p>
 * This is a custom class loader designed to load classes dynamically generated by {@link Invoker}.
 * </p>
 */
public final class InvokerClassLoader extends ClassLoader {

    /**
     * Creates a new {@link ClassLoader} that has specified parent.
     *
     * @param parent Parent {@link ClassLoader}
     */
    public InvokerClassLoader(ClassLoader parent) {
        super(parent);
    }

    /**
     * Loads a class by the class name and the bytecode.
     *
     * @param className The classes name.
     * @param bytecode  The classes bytecode.
     * @return The class.
     * @see ClassLoader#defineClass(String, byte[], int, int)
     */
    public Class<?> createClass(String className, byte[] bytecode) {
        try {
            return this.defineClass(className, bytecode, 0, bytecode.length);
        } catch (LinkageError e) {
            return this.findLoadedClass(className);
        }
    }

}