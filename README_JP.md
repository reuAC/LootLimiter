# LootLimiter
特定ワールド内の指定されたブロックのドロップ品を制限します。目的は、プレイヤーが領地ワールドで鉱物を採掘するのを抑制することです。  
[ [中文](https://github.com/reuAC/LootLimiter/blob/reuAC/README.md) | [English](https://github.com/reuAC/LootLimiter/blob/reuAC/README_EN.md) | 日本語 ]

## 紹介
このプラグインは1.17以上に対応しています。  
制限されるブロックをカスタマイズでき、プレイヤーが制限をトリガーしたときに受け取るメッセージ、スクリーンタイトル、サウンド、パーティクルエフェクトもカスタマイズ可能です。
## コマンド
`/lootimiter` 設定ファイルをリロードします。  
**略称：`/looter`**
## 設定ファイル
プラグインが正常に起動すると、pluginsフォルダ内に設定ファイルが生成されます。場所は `plugins/LootLimiter/config.yml` です。  
以下は設定ファイルの内容と各設定項目の説明です。

```yaml
# 制限対象のブロック
# https://bukkit.windit.net/javadoc/org/bukkit/Material.html
LimitItems:
  - "COAL_ORE" # 石炭鉱石
  - "IRON_ORE" # 鉄鉱石
  - "COPPER_ORE" # 銅鉱石
  - "GOLD_ORE" # 金鉱石
  - "REDSTONE_ORE" # レッドストーン鉱石
  - "DEEPSLATE_GOLD_ORE" # 深層岩の金鉱石
  - "DEEPSLATE_REDSTONE_ORE" # 深層岩のレッドストーン鉱石
  - "EMERALD_ORE" # エメラルド鉱石
  - "DEEPSLATE_EMERALD_ORE" # 深層岩のエメラルド鉱石
  - "LAPIS_ORE" # ラピスラズリ鉱石
  - "DEEPSLATE_LAPIS_ORE" # 深層岩のラピスラズリ鉱石
  - "DIAMOND_ORE" # ダイヤモンド鉱石
  - "DEEPSLATE_DIAMOND_ORE" # 深層岩のダイヤモンド鉱石
  - "NETHER_GOLD_ORE" # ネザー金鉱石
  - "NETHER_QUARTZ_ORE" # ネザー石英鉱石
  - "DEEPSLATE_COAL_ORE" # 深層岩の石炭鉱石
  - "DEEPSLATE_IRON_ORE" # 深層岩の鉄鉱石
# 制限が有効になるワールド名（ワールドは既に存在している必要があります）
LimitWorlds:
  - "world"
# 制限が発動したときにプレイヤーに送信されるメッセージ
Messages:
  # 有効かどうか
  enable: true
  # メッセージ内容
  details:
    - "&c&l嘿! &7这里并不建议挖掘该方块，你的掉落被取消。"
    - "&c&lHey! &7It is not recommended to excavate the cube here, your drop is canceled."
Title:
  # 有効かどうか
  enable: true
  # メインタイトル
  maintitle: "&c&l！"
  # サブタイトル
  subtitle: "&7&l採掘禁止"
  # フェードイン時間
  fadeIn: 10
  # 表示時間
  stay: 70
  # フェードアウト時間
  fadeOut: 20
# 制限が発動したときにプレイヤーに再生されるサウンド
Sound:
  # 有効かどうか
  enable: true
  # 複数のサウンドの再生間隔（単位：ティック、整数）
  interval: 40
  # 音量（浮動小数点数）
  volume: 1.0
  # 音程（浮動小数点数）
  pitch: 0.0
  # サウンドのリスト
  sounds:
    - "AMBIENT_BASALT_DELTAS_ADDITIONS"
    # https://bukkit.windit.net/javadoc/org/bukkit/Sound.html
spawnParticle:
  # 有効かどうか
  enable: true
  # 複数のパーティクルの生成間隔（単位：ティック、整数）
  interval: 40
  # パーティクルの数
  count: 30
  # X軸の最大ランダムオフセット
  offsetX: 1.0
  # Y軸の最大ランダムオフセット
  offsetY: 1.0
  # Z軸の最大ランダムオフセット
  offsetZ: 1.0
  # パーティクルの追加データ、使用されるパーティクルに依存します（通常は速度を表します）
  extra: 0.0
  # 原点修正値。デフォルトではブロックを原点とし、以下のXYZ値を調整することで原点が変更されます。
  revise:
    X: 0.0
    Y: 0.0
    Z: 0.0
  # パーティクルエフェクトのリスト
  particles:
    - "FALLING_HONEY"
    - "LANDING_HONEY"
  # https://bukkit.windit.net/javadoc/org/bukkit/Particle.html
```

## 権限ノード
`lootlimiter.main` リロードコマンドを使用します。  
`lootlimiter.bypass` 制限を受けません。

## 使用方法
1. コンパイル済みのjarファイルをpluginsフォルダに入れ、サーバーを再起動します。