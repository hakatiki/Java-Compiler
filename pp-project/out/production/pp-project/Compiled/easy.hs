import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Load (ImmValue 1) regA,
    WriteInstr regA (DirAddr 1),
    Load (ImmValue 2) regA,
    WriteInstr regA (DirAddr 2),
    Load (ImmValue 3) regA,
    WriteInstr regA (DirAddr 3),
    ReadInstr (IndAddr regSprID),
    Receive regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 4),
    Compute NEq regA regSprID regB,
    Branch regB (Ind regA),
    Jump (Rel (-6)),
    Load (ImmValue 5 ) regA,
    Store regA (DirAddr 0 ),
    Load (DirAddr 0 ) regA,
    WriteInstr regA numberIO,
    ReadInstr (DirAddr 1),
    Receive regA,
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-5)),
    WriteInstr regPC (DirAddr 1),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 43),
    Load (ImmValue 2 ) regA,
    WriteInstr regA numberIO,
    ReadInstr (DirAddr 2),
    Receive regA,
    Load (ImmValue 2) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-5)),
    WriteInstr regPC (DirAddr 2),
    Load (ImmValue 1) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 5),
    Load (ImmValue 3 ) regA,
    WriteInstr regA numberIO,
    WriteInstr regSprID (DirAddr 2),
    Jump (Abs 8),
    ReadInstr (DirAddr 3),
    Receive regA,
    Load (ImmValue 3) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-5)),
    WriteInstr regPC (DirAddr 3),
    Load (ImmValue 1) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 5),
    Load (ImmValue 3 ) regA,
    WriteInstr regA numberIO,
    WriteInstr regSprID (DirAddr 3),
    Jump (Abs 8),
    ReadInstr (DirAddr 2),
    Receive regA,
    Load (ImmValue 2) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    ReadInstr (DirAddr 3),
    Receive regA,
    Load (ImmValue 3) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-9)),
    Load (ImmValue 4 ) regA,
    WriteInstr regA numberIO,
    WriteInstr regSprID (DirAddr 1),
    Jump (Abs 8),
    ReadInstr (DirAddr 1),
    Receive regA,
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    ReadInstr (DirAddr 1),
    Receive regA,
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    ReadInstr (DirAddr 2),
    Receive regA,
    Load (ImmValue 2) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-9)),
    ReadInstr (DirAddr 3),
    Receive regA,
    Load (ImmValue 3) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-14)),
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    WriteInstr regA (DirAddr 1),
    WriteInstr regA (DirAddr 2),
    WriteInstr regA (DirAddr 3),
    EndProg]
main = run [prog]
