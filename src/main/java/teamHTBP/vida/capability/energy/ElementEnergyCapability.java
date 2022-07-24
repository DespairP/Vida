package teamHTBP.vida.capability.energy;

import teamHTBP.vida.element.EnumElements;
import teamHTBP.vida.element.IElement;

public class ElementEnergyCapability implements IElementEnergyCapability {
    //现有的能量
    protected int energy;
    //容量，最大能储存的能力
    protected int capacity;
    //最大一次输入能量
    protected int maxReceive;
    //最大一次输出能量
    protected int maxExtract;
    //能量的元素
    protected IElement element;

    public ElementEnergyCapability(int capacity, int maxReceive, int maxExtract, int energy) {
        this(capacity, maxReceive, maxExtract, energy, EnumElements.VOID);
    }

    public ElementEnergyCapability(int capacity, int maxReceive, int maxExtract, int energy, IElement element) {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.energy = Math.max(0, Math.min(capacity, energy));
        this.element = element;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate, EnumElements element) {
        //如果不能接受返回0
        if (!canReceive(element))
            return 0;
        //应收取的能量，从[容量 - 能量]，[最大一次能收取的能量]，[收取的能量] 中选取最小的
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
        //如果不是模拟就加上应收取的能量
        if (!simulate)
            energy += energyReceived;
        //返回我应该/已经收取的能量
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate, EnumElements element) {
        //如果不能输出返回0
        if (!canExtract(element))
            return 0;
        //应该输出的能量，从[现有能量],[最大输出能量],[输出能量] 中选取最小的
        int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
        //如果不是模拟，就输出能量
        if (!simulate)
            energy -= energyExtracted;
        //已经/应输出的能量
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return this.capacity;
    }

    @Override
    public boolean canExtract(IElement element) {
        return this.maxExtract > 0 && this.energy > 0 && element == this.element;
    }

    @Override
    public boolean canReceive(IElement element) {
        return this.maxReceive > 0 && this.energy <= this.capacity && element == this.element;
    }

    @Override
    public IElement getElement() {
        return this.element;
    }

    @Override
    public void setEnergy(int energy) {
        //用于设置energy,数据的保存等等
        this.energy = energy;
    }
}
