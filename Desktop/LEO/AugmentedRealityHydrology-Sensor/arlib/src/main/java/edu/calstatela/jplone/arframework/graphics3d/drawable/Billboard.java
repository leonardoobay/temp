package edu.calstatela.jplone.arframework.graphics3d.drawable;

import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.util.Log;



import java.nio.FloatBuffer;

import edu.calstatela.jplone.arframework.graphics3d.helper.BufferHelper;
import edu.calstatela.jplone.arframework.graphics3d.helper.ShaderHelper;
import edu.calstatela.jplone.arframework.graphics3d.helper.TextureHelper;
import edu.calstatela.jplone.arframework.graphics3d.matrix.MatrixMath;

public class Billboard implements Drawable {
    private static final String TAG = "waka_Billboard";

    private static final int BYTES_PER_FLOAT = 4;
    private static final int FLOATS_PER_VERTEX = 3;
    private static final int FLOATS_PER_COLOR = 4;
    private static final int FLOATS_PER_TEX_COORD = 2;

    private static FloatBuffer sVertexBuffer = null;
    private static FloatBuffer sTexCoordBuffer = null;
    private static FloatBuffer sColorBuffer = null;
    static int sGLProgramId = 0;

    private int mGLTextureId = 0;

    private static float[] tempDrawMatrix = new float[16];

    private static String shape = "";

    public Billboard(){
    }

    public static void init(){
        sGLProgramId = ShaderHelper.buildShaderProgram(vertexShaderSource, fragmentShaderSource);
        fillBuffersRect();//Modified by fugi
        //fillBufferCircle();//Added by fugi
    }

    public void setTexture(int glTextureId){
        // Make sure texture/bitmap is only set once per object
        if(mGLTextureId != 0)
            return;

        mGLTextureId = glTextureId;
    }

    public void setBitmap(Bitmap bitmap){
        // Make sure texture/bitmap is only set once per object
        if(mGLTextureId != 0)
            return;

        mGLTextureId = TextureHelper.glTextureFromBitmap(bitmap);
    }

    public void delete(){
        TextureHelper.deleteGlTexture(mGLTextureId);
    }

    @Override
    public void draw(float[] matrix){
        if(matrix == null || matrix.length != 16) {
            Log.d(TAG, "Billboard.draw() being called with improper mMatrix");
            return;
        }

        GLES20.glUseProgram(sGLProgramId);

        int positionAttribute = GLES20.glGetAttribLocation(sGLProgramId, "a_Position");
        GLES20.glEnableVertexAttribArray(positionAttribute);
        int colorAttribute = GLES20.glGetAttribLocation(sGLProgramId, "a_Color");
        GLES20.glEnableVertexAttribArray(colorAttribute);
        int texCoordAttribute = GLES20.glGetAttribLocation(sGLProgramId, "a_TexCoord");
        GLES20.glEnableVertexAttribArray(texCoordAttribute);

        int textureUniform = GLES20.glGetUniformLocation(sGLProgramId, "u_Texture");
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mGLTextureId);
        GLES20.glUniform1i(textureUniform, 0);

        int matrixUniform = GLES20.glGetUniformLocation(sGLProgramId, "u_Matrix");
        GLES20.glUniformMatrix4fv(matrixUniform, 1, false, matrix, 0);


        int vertexCount=0;
        if(shape=="rectangle"){
            vertexCount = rectangleVertexFloats.length / FLOATS_PER_VERTEX;
        }else if(shape=="circle"){
            vertexCount = circleVertexFloats.length / FLOATS_PER_VERTEX;
        }
        GLES20.glVertexAttribPointer(positionAttribute, FLOATS_PER_VERTEX, GLES20.GL_FLOAT, false, FLOATS_PER_VERTEX * BYTES_PER_FLOAT, sVertexBuffer);
        GLES20.glVertexAttribPointer(colorAttribute, FLOATS_PER_COLOR, GLES20.GL_FLOAT, false, FLOATS_PER_COLOR * BYTES_PER_FLOAT, sColorBuffer);
        GLES20.glVertexAttribPointer(texCoordAttribute, FLOATS_PER_TEX_COORD, GLES20.GL_FLOAT, false, FLOATS_PER_TEX_COORD * BYTES_PER_FLOAT, sTexCoordBuffer);
        if(shape=="rectangle"){
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        }else if(shape=="circle"){
            GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        }


        GLES20.glDisableVertexAttribArray(positionAttribute);
        GLES20.glDisableVertexAttribArray(colorAttribute);
        GLES20.glDisableVertexAttribArray(texCoordAttribute);
    }

    @Override
    public void draw(float[] projectionMatrix, float[] viewMatrix, float[] modelMatrix){
        tempDrawMatrix = new float[16];
        MatrixMath.multiply3Matrices(tempDrawMatrix, projectionMatrix, viewMatrix, modelMatrix);
        draw(tempDrawMatrix);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //          Drawing Helpers
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static void fillBuffersRect(){
        shape="rectangle";
        sVertexBuffer = BufferHelper.arrayToBuffer(rectangleVertexFloats);
        sColorBuffer = BufferHelper.arrayToBuffer(rectangleColorFloats);
        sTexCoordBuffer = BufferHelper.arrayToBuffer(rectangleTexCoordFloats);
    }
    private static void fillBufferCircle(){
        shape="circle";
        sVertexBuffer = BufferHelper.arrayToBuffer(circleVertexFloats);
        sColorBuffer = BufferHelper.arrayToBuffer(circleColorFloats);
        sTexCoordBuffer = BufferHelper.arrayToBuffer(circleTexCoordFloats);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //          Static Mesh Data
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //Circle Mesh Data
    private static final float[] circleVertexFloats=makeCircle();
    private static float[] makeCircle(){
        int segments = 20;
        float[] vertices = new float[segments * 3+6];

        float angle = (float)(2*Math.PI/segments);
        vertices[0]=0;
        vertices[1]=0;

        for(int i = 0; i < segments; i++){
            vertices[3+(i*3)]=(float)Math.sin(angle * i)*0.5f;
            vertices[3+(i*3+1)]=(float)Math.cos(angle*i)*0.5f;
        }
        vertices[3*segments+3]=0;
        vertices[3*segments+4]=0.5f;

        return vertices;
    }
    private static final float[] circleColorFloats = {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,

            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.2f, 0.5f, 0.0f, 1.0f
    };

    private static final float[] circleTexCoordFloats = circleTexture();
    private static float[] circleTexture(){
        int segments = 20;
        float[] texture = new float[segments * 3+6];
        float angle = (float)(2*Math.PI/segments);

        texture[0]=0.5f;
        texture[1]=0.5f;
        for(int i = 0; i < segments; i++){
            texture[3+(i*3)]=(float)Math.sin(angle * i)*0.5f+0.5f;
            texture[3+(i*3+1)]=(float)Math.cos(angle * i)*0.5f+0.5f;
        }

        return texture;
    }
    //Rectangle Mesh Data
    private static final float[] rectangleVertexFloats = {
            -0.5f,  -0.5f,  0.0f,
            0.5f,   -0.5f,  0.0f,
            0.5f,   0.5f,   0.0f,

            -0.5f,  -0.5f,  0.0f,
            0.5f,   0.5f,   0.0f,
            -0.5f,  0.5f,   0.0f
    };

    private static final float[] rectangleColorFloats = {
            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 1.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,

            1.0f, 0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f, 1.0f,
            0.2f, 0.5f, 0.0f, 1.0f
    };

    private static final float[] rectangleTexCoordFloats = {
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f,

            0.0f, 1.0f,
            1.0f, 0.0f,
            0.0f, 0.0f
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //
    //          Shader Source Code
    //
    ////////////////////////////////////////////////////////////////////////////////////////////////
    private static final String vertexShaderSource =
        "attribute vec4 a_Position;                 \n" +
        "attribute vec4 a_Color;                    \n" +
        "attribute vec2 a_TexCoord;                 \n" +
        "                                           \n" +
        "uniform mat4 u_Matrix;                     \n" +
        "                                           \n" +
        "varying vec2 v_TexCoord;                   \n" +
        "varying vec4 v_Color;                      \n" +
        "                                           \n" +
        "void main()                                \n" +
        "{                                          \n" +
        "    gl_Position = u_Matrix * a_Position;   \n" +
        "    v_Color = a_Color;                     \n" +
        "    v_TexCoord = a_TexCoord;               \n" +
        "}                                          \n";



    private static final String fragmentShaderSource =
        "precision mediump float;                                               \n" +
        "                                                                       \n" +
        "uniform sampler2D u_Texture;                                           \n" +
        "varying vec4 v_Color;                                                  \n" +
        "varying vec2 v_TexCoord;                                               \n" +
        "                                                                       \n" +
        "void main()                                                            \n" +
        "{                                                                      \n" +
        "   gl_FragColor = texture2D(u_Texture, v_TexCoord);                    \n" +
        "}                                                                      \n";

}
