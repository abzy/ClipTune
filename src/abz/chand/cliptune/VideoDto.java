package abz.chand.cliptune;

import java.util.List; 

public class VideoDto { 

	private byte[] video; 
	private String songId; 
	private List<Clip> clips; 

	public VideoDto(byte[] video, String songId) { 
		this.video = video; 
		this.songId = songId; 
	} 

	public VideoDto(){} 

	public byte[] getVideo() { 
		return video; 
	} 

	public void setVideo(byte[] video) { 
		this.video = video; 
	} 

	public String getSongId() { 
		return songId; 
	} 

	public void setSongId(String songId) { 
		this.songId = songId; 
	} 

	public List<Clip> getClips() { 
		return clips; 
	} 

	public void setClips(List<Clip> clips) { 
		this.clips = clips; 
	} 

	public class Clip { 
		private long startTime; 
		private long stopTime; 
		
		public Clip(long startTime, long stopTime) {
			this.startTime = startTime;
			this.stopTime = stopTime;
		}

		public long getStartTime() { 
			return startTime; 
		} 

		public void setStartTime(long startTime) { 
			this.startTime = startTime; 
		} 

		public long getStopTime() { 
			return stopTime; 
		} 

		public void setStopTime(long stopTime) { 
			this.stopTime = stopTime; 
		} 
	} 
}
