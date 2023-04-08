

attribute vec3 a_Position;
attribute vec3 a_Color;
attribute vec2 a_TexCoord1;
attribute vec2 a_TexCoord2;

varying vec3 v_Color;
varying vec2 v_TexCoord1;
varying vec2 v_TexCoord2;

void main() {
    gl_Position = vec4(a_Position, 1.0);
    v_Color = a_Color;
    v_TexCoord1 = a_TexCoord1;
    v_TexCoord2 = a_TexCoord2;
}
