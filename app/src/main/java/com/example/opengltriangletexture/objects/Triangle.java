package com.example.opengltriangletexture.objects;

import static com.example.opengltriangletexture.Constants.BYTES_PER_FLOAT;

import android.opengl.GLES20;

import com.example.opengltriangletexture.programs.TriangleProgram;
import com.example.opengltriangletexture.utils.VertexArray;

import java.nio.ByteBuffer;

public class Triangle {
   private static final int POSITION_COMPONENT_COUNT = 3;
   private static final int COLOR_COMPONENT_COUNT = 3;
   private static final int TEXTURE_COMPONENT_COUNT = 2;

   private static final int STRIDE = (POSITION_COMPONENT_COUNT +
           COLOR_COMPONENT_COUNT + TEXTURE_COMPONENT_COUNT) * BYTES_PER_FLOAT;
   private VertexArray vertexArray;
   private final ByteBuffer indexArray;

   public Triangle() {
       vertexArray = new VertexArray(new float[] {
               //     ---- 位置 ----       ---- 颜色 ----     - 纹理坐标 -
               0.5f,  0.5f, 0.0f,   1.0f, 0.0f, 0.0f,   1.0f, 1.0f,   // 右上
               0.5f, -0.5f, 0.0f,   0.0f, 1.0f, 0.0f,   1.0f, 0.0f,   // 右下
               -0.5f, -0.5f, 0.0f,   0.0f, 0.0f, 1.0f,   0.0f, 0.0f,   // 左下
               -0.5f,  0.5f, 0.0f,   1.0f, 1.0f, 0.0f,   0.0f, 1.0f    // 左上
       });

       indexArray = ByteBuffer.allocateDirect(3 * 2).
               put(new byte[] {
                       0, 1, 3, // 第一个三角形
                       1, 2, 3  // 第二个三角形
               });
       indexArray.position(0);
   }

   public void bindData(TriangleProgram triangleProgram) {
       vertexArray.setVertexAttribPointer(0,
               triangleProgram.getPositionAttributeLocation(),
               POSITION_COMPONENT_COUNT, STRIDE);

       vertexArray.setVertexAttribPointer(3,
               triangleProgram.getColorAttributeLocation(),
               COLOR_COMPONENT_COUNT, STRIDE);

//       vertexArray.setVertexAttribPointer(6,
//               triangleProgram.getTexCoordAttributeLocation(),
//               TEXTURE_COMPONENT_COUNT, STRIDE);

       vertexArray.setVertexAttribPointer(6,
               triangleProgram.getTexture1AttributeLocation(),
               TEXTURE_COMPONENT_COUNT,
               STRIDE);

       vertexArray.setVertexAttribPointer(6,
               triangleProgram.getTexture2AttributeLocation(),
               TEXTURE_COMPONENT_COUNT,
               STRIDE);
   }

   public void draw() {
       GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_BYTE, indexArray);
   }
}
