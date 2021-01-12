#version 150 core

in vec3 vertexColor;
in vec2 textureCoord;
in vec2 positiono;

out vec4 fragColor;

uniform sampler2D texImage;


vec4 normalizeTo(float min, float max, vec4 invec){

	if (invec.x < min) {
	invec.x = min;
	} else if (invec.x > max) {
	invec.x = max;
	}
	if (invec.y < min) {
	invec.y = min;
	}else if (invec.y > max) {
	invec.y = max;
	}
	if (invec.z < min) {
	invec.z = min;
	}else if (invec.z > max) {
	invec.z = max;
	}
	if (invec.w < min) {
	invec.w = min;
	}else if (invec.w > max) {
	invec.w = max;
	}

	return invec;
}


void main() {
	
	
	vec4 baseLight = vec4(0.682, 0.737, 0.741, 1);
	vec4 ambient = baseLight*0.75;
	vec4 lighting = ambient;
	
	int lightSize = 2;
	
	vec2 lightPos[] = vec2[](vec2(0, 0), vec2(500, 500), vec2(10000, 10000));
	
	
	for (int i = 0; i < lightSize; ++i) {
	
	int disX = int(positiono.x - lightPos[i].x);
	int disY = int(positiono.y - lightPos[i].y);
	int distance = int(abs(disX) + abs(disY));
	
	lighting = lighting + normalizeTo(0, 1, (baseLight * (400 - distance)/distance));
	
	}
	

    vec4 textureColor = texture(texImage, textureCoord);
    
     
    
    fragColor = vec4(vertexColor, 1.0) * textureColor * lighting;
}

