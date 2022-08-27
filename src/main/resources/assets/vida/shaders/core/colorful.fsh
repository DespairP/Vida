#version 150

uniform sampler2D Sampler0;

uniform vec4 ColorModulator;

uniform float iTime;
uniform vec2 iResolution;

in vec2 texCoord0;

out vec4 fragColor;

void main() {
    // Normalized pixel coordinates (from 0 to 1)
    vec2 uv = gl_FragCoord.xy/iResolution.xy;

    // Time varying pixel color
    vec3 col = 0.5 + 0.5 * cos(iTime + uv.xyx + vec3(0,2,4));

    // Output to screen
    fragColor = vec4(col, 1.0);
}
