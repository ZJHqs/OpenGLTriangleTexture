precision mediump float;

uniform sampler2D u_Texture1;
uniform sampler2D u_Texture2;

varying vec3 v_Color;
varying vec2 v_TexCoord1;
varying vec2 v_TexCoord2;

void main() {
    gl_FragColor = mix(texture2D(u_Texture1, v_TexCoord1), texture2D(u_Texture2, v_TexCoord2), 0.2)
                            * vec4(v_Color, 1.0);
}
