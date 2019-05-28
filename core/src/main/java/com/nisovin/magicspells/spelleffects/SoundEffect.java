package com.nisovin.magicspells.spelleffects;

import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;

import com.nisovin.magicspells.MagicSpells;

public class SoundEffect extends SpellEffect {
	
	String sound = "random.pop";
	
	float volume = 1.0F;
	
	float pitch = 1.0F;

	@Override
	public void loadFromString(String string) {
		super.loadFromString(string);
		if (string != null && !string.isEmpty()) {
			String[] data = string.split(" ");
			sound = data[0];
			if (data.length > 1) volume = Float.parseFloat(data[1]);
			if (data.length > 2) pitch = Float.parseFloat(data[2]);
			if (sound.equals("random.wood_click")) {
				sound = "random.wood click";
			} else if (sound.equals("mob.ghast.affectionate_scream")) {
				sound = "mob.ghast.affectionate scream";
			}
		}
	}

	@Override
	public void loadFromConfig(ConfigurationSection config) {
		sound = config.getString("sound", sound);
		volume = (float)config.getDouble("volume", volume);
		pitch = (float)config.getDouble("pitch", pitch);
	}
	
	@Override
	public Runnable playEffectLocation(Location location) {
		// TODO non volatile
		MagicSpells.getVolatileCodeHandler().playSound(location, sound, volume, pitch);
		return null;
	}
	
}