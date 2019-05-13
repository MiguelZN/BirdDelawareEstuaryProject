package game;

public enum ClapperRailAsset {
	BACKGROUND("BACKGROUND"),
	ENERGY("ENERGY_ICON"),
	PLATFORM("PLATFORM");

	private String asset_key = null;

	private ClapperRailAsset(String s){
		asset_key=s;
	}
}
