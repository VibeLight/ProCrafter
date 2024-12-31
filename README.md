# ProCrafter
以下に、各カテゴリごとに「引数や設定を変更する部分」「コードを部分的に再利用できる例」「登録方法」を詳細に解説します。それぞれが変更可能なポイントも表形式で示し、コピペしやすい形にします。

---

# **1. アイテム**

## **通常アイテム**
### **登録例**
```java
public static final Item BASIC_ITEM = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(64));
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `maxCount`              | スタック可能数を設定（例: `64`, `1`など）。             |
| `group`                 | アイテムカテゴリ（例: `ItemGroup.FOOD`, `ItemGroup.TOOLS`）。 |

---

## **ツール**
### **登録例**
```java
public static final Item CUSTOM_PICKAXE = new PickaxeItem(ToolMaterials.DIAMOND, 2, -2.8F, new FabricItemSettings().group(ItemGroup.TOOLS));
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `ToolMaterials`         | ツール素材（例: `DIAMOND`, `IRON`, `CUSTOM_MATERIAL`）。|
| `attackDamage`          | ツールの攻撃力（例: `2`）。                          |
| `attackSpeed`           | 攻撃速度（例: `-2.8F`）。                            |

---

## **食べ物**
### **登録例**
```java
public static final Item CUSTOM_FOOD = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(4).saturationModifier(0.3F).build()));
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `hunger`               | 回復する空腹度（例: `4`）。                          |
| `saturationModifier`    | 回復する満腹度（例: `0.3F`）。                       |
| `food`                  | 食べ物プロパティ全体。                                |

---

## **武器**
### **登録例**
```java
public static final Item CUSTOM_SWORD = new SwordItem(ToolMaterials.IRON, 7, -2.4F, new FabricItemSettings().group(ItemGroup.COMBAT));
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `attackDamage`          | 武器の攻撃力（例: `7`）。                            |
| `attackSpeed`           | 攻撃速度（例: `-2.4F`）。                            |

---

## **特殊アイテム**
特別な効果や機能を持つ場合は、独自の`Item`クラスを作成します。

```java
public class CustomEffectItem extends Item {
    public CustomEffectItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (!world.isClient) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200, 1));
        }
        return TypedActionResult.success(player.getStackInHand(hand));
    }
}
```

---

# **2. ブロック**

## **通常ブロック**
```java
public static final Block BASIC_BLOCK = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 6.0F).requiresTool());
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `Material`              | ブロックの材質（例: `STONE`, `WOOD`, `METAL`）。     |
| `strength`              | 硬度と爆破耐性（例: `1.5F, 6.0F`）。                |

---

## **光源ブロック**
```java
public static final Block LIGHT_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS).luminance(15).strength(0.3F));
```

| **変更可能な引数**       | **説明**                                            |
|-------------------------|----------------------------------------------------|
| `luminance`             | 光の強さ（例: `15`）。                               |

---

## **UI入りブロック**
UIが必要な場合、`BlockEntity`と`ScreenHandler`を作成する必要があります。

---

# **3. エンティティ**

エンティティは `EntityType` を登録します。

```java
public static final EntityType<FriendlyEntity> FRIENDLY_ENTITY = Registry.register(
    Registries.ENTITY_TYPE,
    new Identifier("modid", "friendly_entity"),
    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FriendlyEntity::new).dimensions(EntityDimensions.fixed(0.75F, 1.75F)).build()
);
```

---

# **4. バイオームとディメンション**

### **バイオーム**
```java
public static final Biome CUSTOM_BIOME = new Biome.Builder().precipitation(Biome.Precipitation.RAIN).temperature(0.8F).downfall(0.4F).build();
```

### **ディメンション**
ディメンションは `DimensionType` と `ChunkGenerator` を実装します。

---

# 作物(第一部)の作り方

作物そのものを植える場合は、`Item`としての作物アイテムが直接`Block`（作物ブロック）を設置するように動作する必要があります。バニラの耕地ブロック（`Blocks.FARMLAND`）を使用し、特定の条件で作物を直接植える方法について説明します。

---

## **作物そのものを植える方法**

### **概要**
1. 作物アイテムがクリックされたときに、`CropBlock`を耕地上に配置します。
2. バニラの畑 (`Blocks.FARMLAND`) 上にのみ設置できるように制御します。

---

## **必要なコードの実装手順**

### **1. 作物アイテムの作成**
以下のように`BlockItem`を拡張して作物そのものを植えられるようにカスタマイズします。

```java
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
```

### **2. 作物ブロックの作成**
通常の`CropBlock`を使用します。

```java
public class CustomCropBlock extends CropBlock {
    public CustomCropBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        // 耕地ブロックの上にのみ植えることを許可
        return floor.isOf(Blocks.FARMLAND);
    }
}
```

### **3. アイテムとブロックの登録**
`ModBlocks.java`と`ModItems.java`でそれぞれ登録します。

#### **`ModBlocks.java`**
```java
public static final Block CUSTOM_CROP = new CustomCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT));

public static void registerBlocks() {
    Registry.register(Registries.BLOCK, new Identifier("vlhy-procrafter", "custom_crop"), CUSTOM_CROP);
}
```

#### **`ModItems.java`**
```java
public static final Item CUSTOM_CROP_ITEM = new PlaceableCropItem(ModBlocks.CUSTOM_CROP, new FabricItemSettings().group(ItemGroup.MATERIALS));

public static void registerItems() {
    Registry.register(Registries.ITEM, new Identifier("vlhy-procrafter", "custom_crop_item"), CUSTOM_CROP_ITEM);
}
```

### **4. `fabric.mod.json`の更新**
使用するアイテムやブロックのキーを登録します。

---

## **動作のカスタマイズ**

| 機能                    | 方法                                                         |
|-------------------------|------------------------------------------------------------|
| **耕地のみ設置可能にする** | `state.isOf(Blocks.FARMLAND)` でチェック。                   |
| **成長速度を調整する**    | `randomTick` メソッドを`CustomCropBlock`内でオーバーライド。   |
| **設置条件を追加する**    | `useOnBlock` メソッドに高度や周囲の環境チェックを追加。         |

---

## **保存場所**

| ファイル名                     | 保存場所                                                |
|--------------------------------|-------------------------------------------------------|
| `PlaceableCropItem.java`       | `src/main/java/com/vlyh/procrafter/item/custom/PlaceableCropItem.java` |
| `CustomCropBlock.java`         | `src/main/java/com/vlyh/procrafter/block/custom/CustomCropBlock.java` |
| `ModItems.java`                | `src/main/java/com/vlyh/procrafter/registry/ModItems.java` |
| `ModBlocks.java`               | `src/main/java/com/vlyh/procrafter/registry/ModBlocks.java` |

---

## **まとめ**
1. **`PlaceableCropItem`** クラスを作成し、作物アイテムを設置可能にする。
2. **`CustomCropBlock`** を定義し、成長段階や設置条件を制御。
3. 作物を耕地ブロックの上にのみ設置可能に設定。
4. 必要に応じて成長速度や収穫アイテムのドロップをカスタマイズ。

これで、作物そのものを植える機能を簡単に実現できます！

---

## **アイテムのコード集**

| アイテムタイプ | コード例 | 使用シーン | 主な引数の意味 |
|----------------|----------|------------|----------------|
| 通常アイテム | ```java public static final Item MAGIC_DUST = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)); ``` | 魔法やクラフト材料など | `ItemGroup`: インベントリのタブに分類。`maxCount`: 持てるスタック数。 |
| 食べ物 | ```java public static final Item MAGIC_APPLE = new Item(new FabricItemSettings().group(ItemGroup.FOOD).food(new FoodComponent.Builder().hunger(6).saturationModifier(1.2f).alwaysEdible().build())); ``` | 食べ物の追加やHP回復 | `hunger`: 満腹度。`saturationModifier`: 栄養価。`alwaysEdible`: 常に食べられるか。 |
| ツール | ```java public static final Item MAGIC_PICKAXE = new PickaxeItem(ModToolMaterials.MAGIC, 1, -2.8f, new FabricItemSettings().group(ItemGroup.TOOLS)); ``` | 採掘ツールの追加 | `attackDamage`: ダメージ量。`attackSpeed`: 攻撃速度。 |
| 武器 | ```java public static final Item MAGIC_SWORD = new SwordItem(ModToolMaterials.MAGIC, 7, -2.4f, new FabricItemSettings().group(ItemGroup.COMBAT)); ``` | 武器を追加 | `attackDamage`、`attackSpeed` |
| 特殊アイテム | ```java public static final Item MAGIC_WAND = new Item(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1).fireproof()); ``` | 魔法アイテムや特殊用途 | `fireproof`: 火に耐性。 |

---

## **ブロックのコード集**

### **一般ブロック**

| ブロックタイプ | コード例 | 使用シーン | 主な引数の意味 |
|----------------|----------|------------|----------------|
| 一般ブロック | ```java public static final Block MAGIC_ORE = new Block(FabricBlockSettings.of(Material.STONE).strength(3.0f, 3.0f).requiresTool()); ``` | 鉱石、建築ブロック | `strength`: 硬さと爆破耐性。`requiresTool`: 適切なツールが必要か。 |
| 鉱石ブロック | ```java public static final Block MAGIC_ORE = new ExperienceDroppingBlock(FabricBlockSettings.of(Material.STONE).strength(3.0f).requiresTool(), UniformIntProvider.create(2, 5)); ``` | 鉱石の追加 | `UniformIntProvider`: ドロップ経験値の範囲。 |
| 機能付きブロック | ```java public static final Block LAMP_BLOCK = new Block(FabricBlockSettings.of(Material.GLASS).strength(0.3f).luminance(state -> 15)); ``` | 光源ブロックの追加 | `luminance`: 光量（0～15）。 |

### **UIを持つブロック**

| ブロックタイプ | コード例 | 使用シーン | 主な引数の意味 |
|----------------|----------|------------|----------------|
| 機械ブロック | ```java public static final Block MAGIC_MACHINE = new Block(FabricBlockSettings.of(Material.METAL).strength(5.0f).nonOpaque()); ``` | UI付きの工業ブロック | `nonOpaque`: 透明ブロック。 |
| ブロックエンティティ | ```java public static final BlockEntityType<MagicMachineBlockEntity> MAGIC_MACHINE_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("procrafter", "magic_machine"), BlockEntityType.Builder.create(MagicMachineBlockEntity::new, MAGIC_MACHINE).build(null)); ``` | UIとの連携や複雑な機能を持つブロック | `BlockEntityType`: ブロックのエンティティ型。 |

---

## **引数の詳細**

### アイテム
| 引数名 | 説明 |
|--------|------|
| `ItemGroup` | インベントリで表示されるタブの種類。`ItemGroup.MATERIALS`, `ItemGroup.TOOLS`など。 |
| `maxCount` | プレイヤーが1スロットにスタックできる数（例: 64）。 |
| `fireproof` | アイテムが火に耐性を持つ場合に設定。 |
| `food` | 食べ物の特性を設定する。`hunger`, `saturationModifier`など。 |

### ブロック
| 引数名 | 説明 |
|--------|------|
| `Material` | ブロックの材質。`Material.STONE`, `Material.WOOD`, `Material.GLASS`など。 |
| `strength` | ブロックの硬さと爆破耐性を設定する。 |
| `luminance` | ブロックの発光量（0～15）を設定する。 |
| `nonOpaque` | ブロックが透明（光を通す）かどうか。 |

---

## **保存場所**

| ファイル名 | 保存場所 |
|------------|----------|
| **アイテムの登録** (`ModItems.java`) | `src/main/java/com/vlyh/procrafter/registry/ModItems.java` |
| **ブロックの登録** (`ModBlocks.java`) | `src/main/java/com/vlyh/procrafter/registry/ModBlocks.java` |
| **BlockEntityの登録** (`ModBlockEntities.java`) | `src/main/java/com/vlyh/procrafter/registry/ModBlockEntities.java` |

---

## **鉱石、食べ物、ツールを追加する流れ**

1. **`ModItems.java`でアイテムを定義して登録**
   - 鉱石、食べ物、ツールの特性をコードで記述。
2. **`ModBlocks.java`でブロックを定義して登録**
   - 鉱石ブロックや特殊ブロックを定義。
3. **`ModBlockEntities.java`でエンティティを登録**
   - 工業系ブロックの場合、BlockEntityを作成。
4. **UIが必要なら`ScreenHandler`を実装**
   - `ModScreenHandlers.java`でScreenHandlerを登録。
5. **`ModMain.java`で全てを呼び出す**
   - `ModItems.registerItems();`, `ModBlocks.registerBlocks();`など。