package com.game.main;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	
	public static void load(){
		try{
			soundMap.put("laser_sound", new Sound("res/laser_sound.wav"));
			soundMap.put("target_broken_sound", new Sound("res/wood_broken.wav"));
			soundMap.put("mission_completed", new Sound("res/mission_completed.wav"));
			soundMap.put("meteoroid_break", new Sound("res/meteoroid_break.wav"));
			soundMap.put("flyover", new Sound("res/flyover_lower.wav"));
			soundMap.put("mission_failed", new Sound("res/fail_sound.wav"));
		}
		catch (SlickException e){
			e.printStackTrace();
		}
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
}
