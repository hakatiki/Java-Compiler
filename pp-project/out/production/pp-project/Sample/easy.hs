import Sprockell
prog :: [Instruction]
prog = [Load (ImmValue 0 ) regA,
Store regA (DirAddr 0 ),
Load (ImmValue 1 ) regA,
Store regA (DirAddr 4 ),
Load (ImmValue 0 ) regA,
Load (ImmValue 4) regB,
Compute Mul regA regB regA,
Load (ImmValue 0 ) regB,
Compute Add regB regA regA,
Load ( IndAddr regA ) regA,
Push regA,
Load (ImmValue 1 ) regA,
Load (ImmValue 4) regB,
Compute Mul regA regB regA,
Load (ImmValue 0 ) regB,
Compute Add regB regA regA,
Load ( IndAddr regA ) regA,
Pop regB,
Compute Add regB regA regA,
Push regA,
Load (ImmValue 0 ) regA,
Pop regB,
Compute Lt regB regA regA,
Branch regA (Rel 9),
Load (ImmValue 0 ) regA,
Load (ImmValue 4) regB,
Compute Mul regA regB regA,
Load (ImmValue 0 ) regB,
Compute Add regB regA regA,
Load ( IndAddr regA ) regA,
Store regA (DirAddr 8 ),
Jump (Rel 8 ),
Load (ImmValue 1 ) regA,
Load (ImmValue 4) regB,
Compute Mul regA regB regA,
Load (ImmValue 0 ) regB,
Compute Add regB regA regA,
Load ( IndAddr regA ) regA,
Store regA (DirAddr 8 ), EndProg]main = run [prog]
