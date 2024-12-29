public class CABBAGE extends CropBlock {
    public CABBAGE(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        // 耕地ブロックの上にのみ植えることを許可
        return floor.isOf(Blocks.FARMLAND);
    }
}
