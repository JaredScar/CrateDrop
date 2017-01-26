# CrateDrop

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

<h2>Purchase</h2>
[Buy it here] (http://www.mc-market.org/threads/29455/)

<h2>Features</h2>
GUI ✔

Unlimited Crates ✔

Everything Configurable ✔

Air Drops ✔

Add Location Message ✔

Cheap and Affordable ✔

Good Quality ✔

<h2>How It Works</h2>

<h3>Pictures</h3>

<h4>Crate GUI</h4>

![alt text] (http://i.gyazo.com/2f53f8d4c692635c7e1ba4a18a0c5d34.png "Crate GUI")

<h4>Drop Animation</h4>

![alt text] (http://i.gyazo.com/86f71e2e2078c438de5de04b688f2486.gif "Drop Animation")

<h4>Default Messages<h4>

![alt text] (http://i.gyazo.com/2c412b5e95c3311f3f2ce54bd327b70b.png "Default Messages")

<h2>Video Tutorial</h2>
[Video Tutorial] (https://www.youtube.com/watch?v=HyyTL4lOky8)

<h2>Config</h2>
```YAML
###############
### Options ###
###############
Options:
    RadiusMin: 100.0
    RadiusMax: 1000.0
    DropEach: 8600                         ## drop a crate every x seconds
    WorldEnabled: 'Hub'
    CrateMaterialDrop: 'EMERALD_BLOCK'
    CrateMaterial: 'CHEST'
    DropFrom: 200                          ## drop the crates from x distance from the ground, this is the y coordinate it drops from
    DespawnIn: 2200                        ## despawn the dropped crate in x seconds
################
### Messages ###
################
Messages:
    alert_header: '&6&m--------------------------------------------------------'
    alert: '&7A crate has been dropped at (&d{X}&7, &d{Y}&7, &d{Z}&7)...'
    alert_footer: '&6&m--------------------------------------------------------'
    crate_collected: '&e{PLAYER} &7has collected the crate at (&d{X}&7, &d{Y}&7, &d{Z}&7)...'
##############
### Crates ###
##############
CrateList:
- common
- uncommon
- rare
CrateChance:
- 100
- 50
- 20
######################
### Crate Commands ###
######################
CrateCommands:
- 'tell {PLAYER} hi there ;) :chance: 100 :next: tell {PLAYER} this is the second command :chance: 100 :end:'
- ''
- ''
Crates:
    common:
        title: '&aCommon'
        rows: 1
        rewards:
        - 'DIAMOND'
        - 'IRON_INGOT'
        - 'GOLD_INGOT'
        amount:
        - 1
        - 2
        - 3
        enchant:
        - ''
        - ''
        - ''
        displayName:
        - ''
        - ''
        - ''
        lore:
        - '1st lore // 2nd lore // 3rd lore'
        - ''
        - ''
        chance:
        - 100
        - 100
        - 100
    uncommon:
        title: '&dUncommon'
        rows: 1
        rewards:
        - 'DIAMOND'
        - 'IRON_INGOT'
        - 'GOLD_INGOT'
        amount:
        - 10
        - 20
        - 30
        enchant:
        - ''
        - ''
        - ''
        displayName:
        - ''
        - ''
        - ''
        lore:
        - ''
        - ''
        - ''
        chance:
        - 100
        - 100
        - 100
    rare:
        title: '&cRare'
        rows: 1
        rewards:
        - 'DIAMOND'
        - 'IRON_INGOT'
        - 'GOLD_INGOT'
        - 'DIAMOND_SWORD'
        amount:
        - 16
        - 32
        - 64
        - 1
        enchant:
        - ''
        - ''
        - ''
        - 'FIRE_ASPECT:1, DAMAGE_ALL:3'
        displayName:
        - ''
        - ''
        - ''
        - '&7[&aGOD&7] &a&lSword'
        lore:
        - ''
        - ''
        - ''
        - ''
        chance:
        - 100
        - 100
        - 100
        - 100
```        
<h2>Chances in Config Explanation</h2>
You want the CrateChance list to be in order from most common to least common. The CrateList list should be ordered in the same way the CrateChance list is (by corresponding to each index they are in, the indexes must match to be considered it's chance). The most common crate should always have a chance rate of 100 as it will always be there in case none of the other crates are chosen in the chance rate.

Here is an example:
```YAML
CrateList:
- mostcommon # 100% chance if the others don't win
- common # 50% chance
- leastcommon # 10% chance
CrateChance:
- 100
- 50
- 10
```
<h2>Terms Of Service</h2>
1)You may not share this plugin with anyone else at all.

2)You may only use this on your server(s).

3)You may not chargeback at any point.

<h2>Credits:</h2>
Developer- @TheWolfBadger
