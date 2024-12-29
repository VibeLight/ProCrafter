public class PlaceableCropItem extends BlockItem {
    public PlaceableCropItem(Block block, Settings settings) {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);

        // 耕地（FARMLAND）のみ作物を設置可能にする
        if (state.isOf(Blocks.FARMLAND)) {
            BlockPos placePos = pos.up();
            if (world.isAir(placePos)) { // 空気ブロックであるか確認
                world.setBlockState(placePos, this.getBlock().getDefaultState(), 3);
                context.getStack().decrement(1); // アイテムのスタックを減らす
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.PASS; // 条件を満たさない場合は何もしない
    }
}
