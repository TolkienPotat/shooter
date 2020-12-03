package audio;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALC10;
import org.lwjgl.openal.ALCCapabilities;
import org.lwjgl.openal.ALCapabilities;
import org.lwjgl.system.MemoryUtil;





public class AudioMaster {

	private static long device;
	private static ALCCapabilities alcCapabilities;
	private static long context;
	private static ALCapabilities alCapabilities;
	
	private static List<Integer> buffers = new ArrayList<Integer>();
	
	

	public static void init() {
		String defaultDeviceName = ALC10.alcGetString(0, ALC10.ALC_DEFAULT_DEVICE_SPECIFIER);
		device = ALC10.alcOpenDevice(defaultDeviceName);
		alcCapabilities = ALC.createCapabilities(device);
		context = ALC10.alcCreateContext(device, (IntBuffer) null);
		ALC10.alcMakeContextCurrent(context);

		alCapabilities = AL.createCapabilities(alcCapabilities);
	}
	
	public static void setListenerData() {
		AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
		AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);
	}
	
	public void destroy() {
		
		for (int buffer : buffers) {
			AL10.alDeleteBuffers(buffer);
		}
		
		ALC10.alcMakeContextCurrent(MemoryUtil.NULL);
		ALC10.alcDestroyContext(context);
		ALC10.alcCloseDevice(device);

	}
	
	public static int loadSound(String file) throws Exception {
		
		InputStream is = new ByteArrayInputStream(Charset.forName("UTF-16").encode(file).array());
		int buffer = AL10.alGenBuffers();
		buffers.add(buffer);
		WaveDecoder waveFile = new WaveDecoder(is);
		ByteBuffer b = ByteBuffer.wrap(waveFile.in.readAllBytes());
		AL10.alBufferData(buffer, waveFile.fmt, b, (int) waveFile.sampleRate);
//		waveFile.dispose();
		return buffer;
		
	}
}
