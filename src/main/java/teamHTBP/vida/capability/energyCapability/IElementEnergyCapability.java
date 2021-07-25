package teamHTBP.vida.capability.energyCapability;

import teamHTBP.vida.helper.EnumElements;

/**COPY FROM IEnergyStorage**/
public interface IElementEnergyCapability {
    /**
     * Adds energy to the storage. Returns quantity of energy that was accepted.
     *
     * @param maxReceive
     *            Maximum amount of energy to be inserted.
     * @param simulate
     *            If TRUE, the insertion will only be simulated.
     * @param element
     *            ELement of the received energy
     * @return Amount of energy that was (or would have been, if simulated) accepted by the storage.
     */
    int receiveEnergy(int maxReceive, boolean simulate, EnumElements element);

    /**
     * Removes energy from the storage. Returns quantity of energy that was removed.
     *
     * @param maxExtract
     *            Maximum amount of energy to be extracted.
     * @param simulate
     *            If TRUE, the extraction will only be simulated.
     * @param element
     *            Element of the extract Energy
     * @return Amount of energy that was (or would have been, if simulated) extracted from the storage.
     */
    int extractEnergy(int maxExtract, boolean simulate , EnumElements element);

    /**
     * Returns the amount of energy currently stored.
     */
    int getEnergyStored();

    /**
     * Returns the maximum amount of energy that can be stored.
     */
    int getMaxEnergyStored();

    /**
     * Returns if this storage can have energy extracted.
     * If this is false, then any calls to extractEnergy will return 0.
     */
    boolean canExtract(EnumElements element);

    /**
     * Used to determine if this storage can receive energy.
     * If this is false, then any calls to receiveEnergy will return 0.
     */
    boolean canReceive(EnumElements element);

    /**
     * Get the Element of the energy
     * */
    EnumElements getElement();

    /***/
    void setEnergy(int energy);
}
