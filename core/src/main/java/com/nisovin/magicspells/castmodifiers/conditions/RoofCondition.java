package com.nisovin.magicspells.castmodifiers.conditions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.LivingEntity;

import org.jetbrains.annotations.NotNull;

import com.nisovin.magicspells.util.Name;
import com.nisovin.magicspells.util.RegexUtil;
import com.nisovin.magicspells.castmodifiers.Condition;

@Name("roof")
public class RoofCondition extends Condition {

	private int height = 10;
	
	@Override
	public boolean initialize(@NotNull String var) {
		if (RegexUtil.SIMPLE_INT_PATTERN.asMatchPredicate().test(var)) {
			height = Integer.parseInt(var);
		}
		return true;
	}

	@Override
	public boolean check(LivingEntity caster) {
		return hasRoof(caster.getLocation());
	}

	@Override
	public boolean check(LivingEntity caster, LivingEntity target) {
		return hasRoof(target.getLocation());
	}
	
	@Override
	public boolean check(LivingEntity caster, Location location) {
		return hasRoof(location);
	}

	private boolean hasRoof(Location location) {
		Block b = location.clone().add(0, 2, 0).getBlock();
		for (int i = 0; i < height; i++) {
			if (!b.getType().isAir()) return true;
			b = b.getRelative(BlockFace.UP);
		}
		return false;
	}

}
