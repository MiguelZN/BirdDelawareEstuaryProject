package game;

public enum ClapperRailAsset {
	BACKGROUND("BACKGROUND"),
	ENERGY("ENERGY_ICON");

	private String asset_key = null;

	private ClapperRailAsset(String s){
		asset_key=s;
	}
}
