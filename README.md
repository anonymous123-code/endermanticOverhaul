# Endermantic Overhaul

A mod about controlling endermen

## Additions / Changes:

### Ender Force Concentrator:

- Forces Endermen to teleport to facing block when activated via Redstone
- Recipe:

| Ender pearl |  Iron Bars   | Ender pearl |
|:-----------:|:------------:|:-----------:|
|  Endstone   | Chorus Fruit |  Endstone   |
|  Endstone   |   Endstone   |  Endstone   |

### Negativ Ender Force Emitter:

- Forces Endermen to place a Block at the Facing position whe activated via Redstone
- Planned for 1.19 (=>Tbd): Consumes 1 Sculk charge per block
- Recipe:
    - Converted from Ender Force Concentrator when placed (unforced) by an Enderman in the Nether

### Dispenser Behavior:

- When a Bock is dispensed on an Enderman and the block-specific actions fail, the Enderman will take that Block
- The Blockstate is the Blockstate which would result when the Block is placed in front of the Dispenser. Because
  Endermen don't update the blocks they placed, this currently _**results in usually impossible Blockstate
  configurations**_ like "Wall Slabs". I'm not sure if I should fix it or not as it seems like an interesting mechanic.
- This bug also appears when Blocks, where `getPlacementState()` differ from their default State, are added
  to `#endermen_holdable`

My first mod
