package jasmine.framework.common.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 *
 * <p>
 * 文件工具类，类路径下的资源以"classpath:"开头，除此之外表示文件。
 * </p>
 *
 * @author mh.z
 */
public class ResourceUtil {
    public static final String CLASS_PATH_PREFIX = "classpath:";
    public static final String FILE_PATH_PREFIX = "file:";

    /**
     * 从指定资源中读取字符串
     *
     * @param path
     * @return
     */
    @Nonnull
    public static String getStringFromPath(@Nonnull String path) {
        CheckUtil.notNull(path, "path null");

        return getStringFromPath(path, StandardCharsets.UTF_8.name());
    }

    /**
     * 从指定资源中读取字符串
     *
     * @param path
     * @param charsetName
     * @return
     */
    @Nonnull
    public static String getStringFromPath(@Nonnull String path, @Nonnull String charsetName) {
        CheckUtil.notNull(path, "path null");
        CheckUtil.notNull(charsetName, "charsetName null");

        InputStream inputStream = getInputStreamFromPath(path);
        checkInputStream(path, inputStream);

        try {
            return getStringFromInputStream(inputStream, charsetName);
        } finally {
            closeInputStream(inputStream);
        }
    }

    /**
     * 从输入流中读取字符串
     *
     * @param inputStream
     * @param charsetName
     * @return
     */
    @Nonnull
    public static String getStringFromInputStream(@Nonnull InputStream inputStream, @Nonnull String charsetName) {
        CheckUtil.notNull(inputStream, "inputStream null");
        CheckUtil.notNull(charsetName, "charsetName null");

        byte[] bytes = getBytesFromInputStream(inputStream);
        return getStringFromBytes(bytes, charsetName);
    }

    /**
     * 转换字节数组成字符串
     *
     * @param bytes
     * @param charsetName
     * @return
     */
    @Nonnull
    public static String getStringFromBytes(@Nonnull byte[] bytes, @Nonnull String charsetName) {
        CheckUtil.notNull(bytes, "bytes null");
        CheckUtil.notNull(charsetName, "charsetName null");

        try {
            return new String(bytes, charsetName);
        } catch (UnsupportedEncodingException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    /**
     * 从输入流中读取字节数组
     *
     * @param inputStream
     * @return
     */
    @Nonnull
    public static byte[] getBytesFromInputStream(@Nonnull InputStream inputStream) {
        CheckUtil.notNull(inputStream, "inputStream null");

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length = -1;

            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw ErrorUtil.sneakyError(e);
        }
    }

    /**
     * 打开指定资源的输入流
     *
     * @param path
     * @return
     */
    @Nullable
    public static InputStream getInputStreamFromPath(@Nonnull String path) {
        CheckUtil.notNull(path, "path null");
        InputStream inputStream = null;

        try {
            if (path.startsWith(CLASS_PATH_PREFIX)) {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                String resourceName = path.substring(10);

                if (resourceName.startsWith("/")) {
                    resourceName = resourceName.substring(1);
                }

                inputStream = loader.getResourceAsStream(resourceName);
            } else {
                String fileName;

                if (path.startsWith(FILE_PATH_PREFIX)) {
                    fileName = path.substring(5);
                } else {
                    fileName = path;
                }

                inputStream = new FileInputStream(fileName);
            }
        } catch (FileNotFoundException e) {
            //
        }

        return inputStream;
    }

    /**
     * 检查输入流
     *
     * @param path
     * @param inputStream
     */
    public static void checkInputStream(@Nonnull String path, InputStream inputStream) {
        if (inputStream == null) {
            throw new RuntimeException("not found file '" + path + "'");
        }
    }

    /**
     * 关闭输入流
     *
     * @param inputStream
     */
    public static void closeInputStream(@Nullable InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                //
            }
        }
    }

}
