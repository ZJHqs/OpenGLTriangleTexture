package com.example.opengltriangletexture.utils;

import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLUtils.texImage2D;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.util.Log;

public class TextureHelper {

    private static final String TAG = "TextureHelper";

    public static int loadTexture(Context context, int resourceId) {
        final int[] textureObjectIds = new int[1];

        GLES20.glGenTextures(1, textureObjectIds, 0);

        if (textureObjectIds[0] == 0) {
            Log.w(TAG, "Could not generate a new OpenGL texture object.");
            return 0;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        final Bitmap bitmap = BitmapFactory.decodeResource(
                context.getResources(), resourceId, options);

        if (bitmap == null) {
            Log.w(TAG, "Resource ID " + resourceId + " could not be decoded.");

            GLES20.glDeleteTextures(1, textureObjectIds, 0);
            return 0;
        }

        GLES20.glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);

        GLES20.glTexParameteri(GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);

        texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);

        bitmap.recycle();

        GLES20.glGenerateMipmap(GL_TEXTURE_2D);

        GLES20.glBindTexture(GL_TEXTURE_2D, 0);

        return textureObjectIds[0];
    }

}
