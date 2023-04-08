package com.example.opengltriangletexture.programs;

import static android.opengl.GLES20.GL_TEXTURE0;
import static android.opengl.GLES20.GL_TEXTURE1;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glUniform1i;
import static android.opengl.GLES20.glUseProgram;

import android.content.Context;
import android.opengl.GLES20;

import com.example.opengltriangletexture.R;
import com.example.opengltriangletexture.utils.ShaderHelper;
import com.example.opengltriangletexture.utils.TextResourceReader;

import java.util.ArrayList;

public class TriangleProgram {

//    private static final String U_TEXTURE = "u_Texture";
    private static final String U_TEXTURE1 = "u_Texture1";
    private static final String U_TEXTURE2 = "u_Texture2";
//    private static final String U_MATRIX = "u_Matrix";
    private static final String A_POSITION = "a_Position";
    private static final String A_COLOR = "a_Color";
//    private static final String A_TEXCOORD = "a_TexCoord";
private static final String A_TEXCOORD1 = "a_TexCoord1";
    private static final String A_TEXCOORD2 = "a_TexCoord2";


//    private final int uTextureLocation;
    private final int uTextureLocation1;
    private final int uTextureLocation2;
//    private final int uMatrixLocation;
    private final int aPositionLocation;
    private final int aColorLocation;
//    private final int aTexCoordLocation;
    private final int aTexCoordLocation1;
    private final int aTexCoordLocation2;


    private final int program;

    public TriangleProgram(Context context) {
        program = ShaderHelper.buildProgram(
                TextResourceReader.readTextFileFromResource(
                        context, R.raw.triangle_vertex_shader),
                TextResourceReader.readTextFileFromResource(
                        context, R.raw.triangle_fragment_shader));

//        uMatrixLocation = GLES20.glGetUniformLocation(program, U_MATRIX);
//        uTextureLocation = GLES20.glGetUniformLocation(program, U_TEXTURE);
        uTextureLocation1 = GLES20.glGetUniformLocation(program, U_TEXTURE1);
        uTextureLocation2 = GLES20.glGetUniformLocation(program, U_TEXTURE2);


        aPositionLocation = GLES20.glGetAttribLocation(program, A_POSITION);
        aColorLocation = GLES20.glGetAttribLocation(program, A_COLOR);
//        aTexCoordLocation = GLES20.glGetAttribLocation(program, A_TEXCOORD);
        aTexCoordLocation1 = GLES20.glGetAttribLocation(program, A_TEXCOORD1);
        aTexCoordLocation2 = GLES20.glGetAttribLocation(program, A_TEXCOORD2);
    }

    public int getPositionAttributeLocation() {
        return aPositionLocation;
    }

    public int getColorAttributeLocation() {
        return aColorLocation;
    }

//    public int getTexCoordAttributeLocation() {
//        return aTexCoordLocation;
//    }

    public int getTexture1AttributeLocation() {
        return aTexCoordLocation1;
    }

    public int getTexture2AttributeLocation() {
        return aTexCoordLocation2;
    }

    public void useProgram() {
        // Set the current OpenGL shader program to this program.
        glUseProgram(program);
    }

    public void setUniforms(float[] matrix, int textureId) {
//        GLES20.glUniformMatrix4fv(uMatrixLocation, 1, false, matrix, 0);

        GLES20.glActiveTexture(GL_TEXTURE0);

        GLES20.glBindTexture(GL_TEXTURE_2D, textureId);

//        glUniform1i(uTextureLocation, 0);
    }

    public void setUniforms(int textureId1, int textureId2) {
        GLES20.glActiveTexture(GL_TEXTURE0);

        GLES20.glBindTexture(GL_TEXTURE_2D, textureId1);

        GLES20.glActiveTexture(GL_TEXTURE1);

        GLES20.glBindTexture(GL_TEXTURE_2D, textureId2);

        glUniform1i(uTextureLocation1, 0);
        glUniform1i(uTextureLocation2, 1);
    }
}
