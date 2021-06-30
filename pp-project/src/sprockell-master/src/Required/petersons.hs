import Sprockell
prog :: [Instruction]
prog = [
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Load (ImmValue 1) regA,
    WriteInstr regA (DirAddr 1),
    Load (ImmValue 2) regA,
    WriteInstr regA (DirAddr 2),
    ReadInstr (IndAddr regSprID),
    Receive regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 4),
    Compute NEq regA regSprID regB,
    Branch regB (Ind regA),
    Jump (Rel (-6)),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 3),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 4),
    Load (ImmValue 1) regA,
    WriteInstr regA (DirAddr 5),
    Load (ImmValue 100) regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (DirAddr 1),
    Receive regA,
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 1),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 72),
    Compute Add regSprID reg0 regA,
    Push regA,
    Load (ImmValue 1) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Equal regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 4),
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (DirAddr 0) regA,
    Load (ImmValue 3) regE,
    Compute Add regA regE regE,
    Load (ImmValue 1) regA,
    WriteInstr regA (IndAddr regE),
    Load (DirAddr 1) regA,
    Load (ImmValue 3) regB,
    Compute Add regB regA regA,
    ReadInstr (IndAddr regA),
    Receive regA,
    Push regA,
    Load (ImmValue 0) regA,
    Push regA,
    ReadInstr (DirAddr 5),
    Receive regA,
    Pop regB,
    Compute And regB regA regA,
    Pop regB,
    Compute Equal regB regA regA,
    Push regA,
    Load (DirAddr 1) regA,
    Pop regB,
    Compute Equal regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 2),
    Jump (Rel  (-20)),
    ReadInstr (DirAddr 6),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (DirAddr 6),
    Receive regA,
    WriteInstr regA numberIO,
    ReadInstr (DirAddr 6),
    Receive regA,
    Push regA,
    Load (ImmValue 45) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (DirAddr 6),
    Receive regA,
    WriteInstr regA numberIO,
    Load (DirAddr 0) regA,
    Load (ImmValue 3) regE,
    Compute Add regA regE regE,
    Load (ImmValue 0) regA,
    WriteInstr regA (IndAddr regE),
    WriteInstr regSprID (DirAddr 1),
    Jump (Abs 6),
    ReadInstr (DirAddr 2),
    Receive regA,
    Load (ImmValue 2) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 2),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 72),
    Compute Add regSprID reg0 regA,
    Push regA,
    Load (ImmValue 1) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 3),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Equal regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 4),
    Load (ImmValue 1) regA,
    Store regA (DirAddr 3),
    Jump (Rel 1),
    Load (DirAddr 2) regA,
    Load (ImmValue 3) regE,
    Compute Add regA regE regE,
    Load (ImmValue 1) regA,
    WriteInstr regA (IndAddr regE),
    Load (DirAddr 3) regA,
    Load (ImmValue 3) regB,
    Compute Add regB regA regA,
    ReadInstr (IndAddr regA),
    Receive regA,
    Push regA,
    Load (ImmValue 0) regA,
    Push regA,
    ReadInstr (DirAddr 5),
    Receive regA,
    Pop regB,
    Compute And regB regA regA,
    Pop regB,
    Compute Equal regB regA regA,
    Push regA,
    Load (DirAddr 3) regA,
    Pop regB,
    Compute Equal regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 2),
    Jump (Rel  (-20)),
    ReadInstr (DirAddr 6),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (DirAddr 6),
    Receive regA,
    WriteInstr regA numberIO,
    ReadInstr (DirAddr 6),
    Receive regA,
    Push regA,
    Load (ImmValue 45) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (DirAddr 6),
    Receive regA,
    WriteInstr regA numberIO,
    Load (DirAddr 2) regA,
    Load (ImmValue 3) regE,
    Compute Add regA regE regE,
    Load (ImmValue 0) regA,
    WriteInstr regA (IndAddr regE),
    WriteInstr regSprID (DirAddr 2),
    Jump (Abs 6),
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
    ReadInstr (DirAddr 6),
    Receive regA,
    WriteInstr regA numberIO,
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    WriteInstr regA (DirAddr 1),
    WriteInstr regA (DirAddr 2),
    EndProg]
main = run [prog,prog,prog]
