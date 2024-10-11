# LootLimiter
Limits specific block drops in designated worlds. The purpose is to guide players away from mining ores in the claimed world.  
[ [中文](https://github.com/reuAC/LootLimiter/blob/reuAC/README.md) | English | [日本語](https://github.com/reuAC/LootLimiter/blob/reuAC/README_JP.md) ]

## Introduction
This plugin is compatible with 1.17 and above.  
You can customize the restricted blocks and configure the messages, screen titles, sounds, and particle effects players receive when the limitation is triggered.
## Commands
`/lootlimiter` Reloads the configuration.  
**Alias：`/looter`**

## Configuration File
After the plugin starts successfully, a configuration file will be generated under the plugins folder, located at `plugins/LootLimiter/config.yml`  
Here is the configuration file content and explanations for each setting:  
```yaml
# Restricted blocks
# https://bukkit.windit.net/javadoc/org/bukkit/Material.html
LimitItems:
  - "COAL_ORE" # Coal Ore
  - "IRON_ORE" # Iron Ore
  - "COPPER_ORE" # Copper Ore
  - "GOLD_ORE" # Gold Ore
  - "REDSTONE_ORE" # Redstone Ore
  - "DEEPSLATE_GOLD_ORE" # Deepslate Gold Ore
  - "DEEPSLATE_REDSTONE_ORE" # Deepslate Redstone Ore
  - "EMERALD_ORE" # Emerald Ore
  - "DEEPSLATE_EMERALD_ORE" # Deepslate Emerald Ore
  - "LAPIS_ORE" # Lapis Lazuli Ore
  - "DEEPSLATE_LAPIS_ORE" # Deepslate Lapis Lazuli Ore
  - "DIAMOND_ORE" # Diamond Ore
  - "DEEPSLATE_DIAMOND_ORE" # Deepslate Diamond Ore
  - "NETHER_GOLD_ORE" # Nether Gold Ore
  - "NETHER_QUARTZ_ORE" # Nether Quartz Ore
  - "DEEPSLATE_COAL_ORE" # Deepslate Coal Ore
  - "DEEPSLATE_IRON_ORE" # Deepslate Iron Ore
# The world(s) where the limitation applies (world must already exist)
LimitWorlds:
  - "world"
# Message sent to the player when the limitation is triggered
Messages:
  # Enable or disable messages
  enable: true
  # Message content
  details:
    - "&c&lHey! &7It is not recommended to mine this block here, your drop has been canceled."
    - "&c&lHey! &7It is not recommended to excavate the cube here, your drop is canceled."
# Screen title information displayed to the player when the limitation is triggered
Title:
  # Enable or disable screen title
  enable: true
  # Main title
  maintitle: "&c&l!"
  # Subtitle
  subtitle: "&7&lMining Prohibited"
  # Fade-in time
  fadeIn: 10
  # Display duration
  stay: 70
  # Fade-out time
  fadeOut: 20
# Sounds sent to the player when the limitation is triggered
Sound:
  # Enable or disable sounds
  enable: true
  # Interval between multiple sounds (in ticks, integer)
  interval: 40
  # Volume (float)
  volume: 1.0
  # Pitch (float)
  pitch: 0.0
  # List of sounds
  sounds:
    - "AMBIENT_BASALT_DELTAS_ADDITIONS"
    # https://bukkit.windit.net/javadoc/org/bukkit/Sound.html
spawnParticle:
  # Enable or disable particles
  enable: true
  # Interval between particle spawns (in ticks, integer)
  interval: 40
  # Number of particles
  count: 30
  # Maximum random offset on the X-axis
  offsetX: 1.0
  # Maximum random offset on the Y-axis
  offsetY: 1.0
  # Maximum random offset on the Z-axis
  offsetZ: 1.0
  # Extra data for the particle, depending on the particle type (usually indicates speed)
  extra: 0.0
  # Origin adjustment, default is block origin, you can adjust the XYZ values below to change the origin.
  revise:
    X: 0.0
    Y: 0.0
    Z: 0.0
  # List of particle effects
  particles:
    - "FALLING_HONEY"
    - "LANDING_HONEY"
  # https://bukkit.windit.net/javadoc/org/bukkit/Particle.html
```

## Permission Nodes
`lootlimiter.main` To use the reload command.  
`lootlimiter.bypass` To bypass the restrictions.

## Usage
1. Place the compiled jar file into the plugins folder and restart the server.