package com.example.opengltriangletexture.programs;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUseProgram;

import android.content.Context;
import android.opengl.GLES20;

import com.example.opengltriangletexture.R;
import com.example.opengltriangletexture.utils.ShaderHelper;
import com.example.opengltriangletexture.utils.TextResourceReader;

public class TriangleProgram {

    private static final String U_TEXTURE = "u_Texture";
//    private static final String U_MATRIX = "u_Matrix";
    private static final String A_POSITION = "a_Position";
    private static final String A_COLOR = "a_Color";
    private static final String A_TEXCOORD = "a_TexCoord";


    private final int uTextureLocation;
//    private final int uMatrixLocation;
    private final int aPositionLocation;
    private final int aColorLocation;
    private final int aTexCoordLocation;

    private final int program;

    public TriangleProgram(Context context) {
        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(
                        context, R.raw.triangle_vertex_shader),
                TextResourceReader.readTextFileFromResource(
                        context, R.raw.triangle_fragment_shader));

//        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
        uTextureLocation = GLES20.glGetUniformLocation(program, U_TEXTURE);

        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);
        aTexCoordLocation = GLES20.glGetAttribLocation(program, A_TEXCOORD);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getColorAttributeLocation() {
        return aColorLocation;
    }

    public int getTexCoordAttributeLocation() {
        return aTexCoordLocation;
    }

    public void useProgram() {
        // Set the current OpenGL shader program to this program.
        glUseProgram(program);
    }

    public void setUniforms(float[] matrix, int textureId) {
//        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        GLES20.glActiveTexture(GL_TEXTURE0);

        GLES20.glBindTexture(GL_TEXTURE_2D, textureId);

        glUniform1i(uTextureLocation, 0);
    }

    public void setUniforms(int textureId) {
        GLES20.glActiveTexture(GL_TEXTURE0);

        GLES20.glBindTexture(GL_TEXTURE_2D, textureId);

        glUniform1i(uTextureLocation, 0);
    }
}
