package game;

public enum ClapperRailAsset {
	BACKGROUND("BACKGROUND"),
	ENERGY("ENERGY_ICON"),
	CRAB("CRAB"),
	STICK("STICK"),
	PLATFORM("PLATFORM");

	private String asset_key = null;

	private ClapperRailAsset(String s){
		asset_key=s;
	}
}
