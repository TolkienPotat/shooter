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
	vec4 ambient = baseLight*0.65;
	vec4 lighting = ambient;
	
	int lightSize = 11;
	
	vec3 lightPos[] = vec3[](vec3(320, 440, 400), vec3(10000, 10000, 500), vec3(2000, 1000, 400), vec3(3200, 600, 400), vec3(3960, 640, 400), vec3(3960, 200, 400)
	, vec3(2640, 1000, 400), vec3(2200, 160, 400), vec3(1040, 120, 400), vec3(4000, 2000, 1000), vec3(520, 1880, 500));
	
	
	for (int i = 0; i < lightSize; ++i) {
	
	int disX = int(positiono.x - lightPos[i].x);
	int disY = int(positiono.y - lightPos[i].y);
	float distance = abs(disX) + abs(disY);
	
	lighting = lighting + normalizeTo(0, 1, (baseLight * (lightPos[i].z + 10 - distance)/lightPos[i].z));
	
	}
	

    vec4 textureColor = texture(texImage, textureCoord);
    
     
    
    fragColor = vec4(vertexColor, 1.0) * textureColor * normalizeTo(0, 1.5, lighting);
}

