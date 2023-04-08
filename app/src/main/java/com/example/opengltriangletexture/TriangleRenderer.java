package com.example.opengltriangletexture;

import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;
import static com.example.opengltriangletexture.Constants.BYTES_PER_FLOAT;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.example.opengltriangletexture.objects.Triangle;
import com.example.opengltriangletexture.programs.TriangleProgram;
import com.example.opengltriangletexture.utils.MatrixHelper;
import com.example.opengltriangletexture.utils.ShaderHelper;
import com.example.opengltriangletexture.utils.TextResourceReader;
import com.example.opengltriangletexture.utils.TextureHelper;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class TriangleRenderer implements GLSurfaceView.Renderer {

    private static final boolean DBG = false;

    private final Context context;

    private TriangleProgram triangleProgram;
    private Triangle triangle;
    private int texture1;
    private int texture2;


    private float[] projectionMatrix = new float[16];
//    private final float[] modelMatrix = new float[16];
//    private int uMatrixLocation;

    public TriangleRenderer(Context context) {
        this.context = context;
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f, 0f, 0f, 0f);

        triangleProgram = new TriangleProgram(context);
        triangle = new Triangle();

        texture1 = TextureHelper.loadTexture(context, R.drawable.wall);
        texture2 = TextureHelper.loadTexture(context, R.drawable.awesomeface);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
//        GLES20.glViewport(0, 0, width, height);
        final float aspectRatio = width > height ?
                (float) width / (float) height :
                (float) height / (float) width;
        if (width > height) {
            orthoM(projectionMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);
        }
        else {
            orthoM(projectionMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        drawTriangles();
    }

    private void drawTriangles() {
        triangleProgram.useProgram();
//        triangleProgram.setUniforms(projectionMatrix, texture);
        triangleProgram.setUniforms(texture1, texture2);

        triangle.bindData(triangleProgram);
        triangle.draw();
    }
}
