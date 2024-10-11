# LootLimiter
限制指定世界内的特定方块掉落产物。初衷为引导玩家不在领地世界挖掘矿物。  
[ 中文 | [English](https://github.com/reuAC/LootLimiter/blob/reuAC/README_EN.md) | [日本語](https://github.com/reuAC/LootLimiter/blob/reuAC/README_JP.md) ]

## 介绍
该插件适用于1.17及以上。  
支持自定义受限方块，并能够自定义玩家触发限制之后所收到的信息、屏幕标题、声音、粒子效果。
## 指令
`/lootlimiter` 重载配置。  
**缩写：`/looter`**

## 配置文件
插件成功启动后，会在plugins文件夹下生成配置文件，位于 `plugins/LootLimiter/config.yml`  

下面是配置文件内容及其对应的设置项的注释。

```yaml
# 作用于的方块
# https://bukkit.windit.net/javadoc/org/bukkit/Material.html
LimitItems:
  - "COAL_ORE" # 煤矿石
  - "IRON_ORE" # 铁矿石
  - "COPPER_ORE" # 铜矿石
  - "GOLD_ORE" # 金矿石
  - "REDSTONE_ORE" # 红石矿石
  - "DEEPSLATE_GOLD_ORE" # 深板岩金矿石
  - "DEEPSLATE_REDSTONE_ORE" # 深板岩红石矿石
  - "EMERALD_ORE" # 绿宝石矿石
  - "DEEPSLATE_EMERALD_ORE" # 深板岩绿宝石矿石
  - "LAPIS_ORE" # 青金石矿石
  - "DEEPSLATE_LAPIS_ORE" # 深板岩青金石矿石
  - "DIAMOND_ORE" # 钻石矿石
  - "DEEPSLATE_DIAMOND_ORE" # 深板岩钻石矿石
  - "NETHER_GOLD_ORE" # 下界金矿石
  - "NETHER_QUARTZ_ORE" # 下界石英矿石
  - "DEEPSLATE_COAL_ORE" # 深板岩煤矿石
  - "DEEPSLATE_IRON_ORE" # 深板岩铁矿石
# 限制机制生效的世界名称（要求世界已经存在）
LimitWorlds:
  - "world"
# 触发限制后对玩家发送的信息
Messages:
  # 是否启用
  enable: true
  # 信息内容
  details:
    - "&c&l嘿! &7这里并不建议挖掘该方块，你的掉落被取消。"
    - "&c&lHey! &7It is not recommended to excavate the cube here, your drop is canceled."
# 触发限制后对玩家发送的屏幕标题信息
Title:
  # 是否启用
  enable: true
  # 主标题
  maintitle: "&c&l！"
  # 副标题
  subtitle: "&7&l挖掘禁止"
  # 淡入时间
  fadeIn: 10
  # 停留/展示时长
  stay: 70
  # 淡出时间
  fadeOut: 20
# 触发限制后对玩家发送的声音
Sound:
  # 是否启用
  enable: true
  # 多个粒子之间播放的间隔（单位为ticks，整数）
  interval: 40
  # 音量（浮点数）
  volume: 1.0
  # 音高（浮点数）
  pitch: 0.0
  # 声音列表
  sounds:
    - "AMBIENT_BASALT_DELTAS_ADDITIONS"
    # https://bukkit.windit.net/javadoc/org/bukkit/Sound.html
# 触发限制后在被破坏方块处生成的粒子效果
spawnParticle:
  # 是否启用
  enable: true
  # 多个粒子之间生成的间隔（单位为ticks，整数）
  interval: 40
  # 粒子的数量
  count: 30
  # X轴上的最大随机偏移量
  offsetX: 1.0
  # Y轴上的最大随机偏移量
  offsetY: 1.0
  # Z轴上的最大随机偏移量
  offsetZ: 1.0
  # 此粒子的额外数据，取决于所使用的粒子（通常表示速度）
  extra: 0.0
  # 原点修正量，默认以方块为原点，可以调整下面的XYZ数值，原点则会有加减变化。
  revise:
    X: 0.0
    Y: 0.0
    Z: 0.0
  # 粒子效果名称列表
  particles:
    - "FALLING_HONEY"
    - "LANDING_HONEY"
  # https://bukkit.windit.net/javadoc/org/bukkit/Particle.html
```

## 权限节点
`lootlimiter.main` 使用重载指令。  
`lootlimiter.bypass` 不被限制所影响。

## 使用方法
1. 将编译完成的jar包放入plugins文件夹中，重启服务器。
