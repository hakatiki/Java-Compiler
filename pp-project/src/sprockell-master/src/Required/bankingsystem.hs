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
    Load (ImmValue 4) regA,
    WriteInstr regA (DirAddr 4),
    Load (ImmValue 5) regA,
    WriteInstr regA (DirAddr 5),
    Load (ImmValue 6) regA,
    WriteInstr regA (DirAddr 6),
    ReadInstr (IndAddr regSprID),
    Receive regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 4),
    Compute NEq regA regSprID regB,
    Branch regB (Ind regA),
    Jump (Rel (-6)),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 1),
    Receive regA,
    Load (ImmValue 1) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 1),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 1),
    Jump (Abs 14),
    ReadInstr (DirAddr 2),
    Receive regA,
    Load (ImmValue 2) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 2),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 2),
    Jump (Abs 14),
    ReadInstr (DirAddr 3),
    Receive regA,
    Load (ImmValue 3) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 3),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 3),
    Jump (Abs 14),
    ReadInstr (DirAddr 4),
    Receive regA,
    Load (ImmValue 4) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 4),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 4),
    Jump (Abs 14),
    ReadInstr (DirAddr 5),
    Receive regA,
    Load (ImmValue 5) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 5),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 5),
    Jump (Abs 14),
    ReadInstr (DirAddr 6),
    Receive regA,
    Load (ImmValue 6) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-4)),
    WriteInstr regPC (DirAddr 6),
    Load (ImmValue 0) regA,
    Compute Equal regSprID regA regB,
    Branch regB (Rel 93),
    Compute Add regSprID reg0 regA,
    Store regA (DirAddr 0),
    Load (ImmValue 0) regA,
    Store regA (DirAddr 1),
    Load (DirAddr 0) regA,
    Push regA,
    Load (ImmValue 3) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 41),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 27) regA,
    Pop regB,
    Compute Sub regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    Jump (Rel 40),
    Load (DirAddr 1) regA,
    Compute Equal regA reg0 regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 36),
    TestAndSet (DirAddr 0),
    Receive regD,
    Load (ImmValue 1) regC,
    Compute NEq regD regC regD,
    Branch regD (Rel (-4)),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    Store regA (DirAddr 2),
    Load (DirAddr 2) regA,
    Push regA,
    Load (ImmValue 0) regA,
    Pop regB,
    Compute Gt regB regA regA,
    Compute Equal regA reg0 regA,
    Branch regA (Rel 14),
    ReadInstr (DirAddr 7),
    Receive regA,
    Push regA,
    Load (ImmValue 32) regA,
    Pop regB,
    Compute Add regB regA regA,
    WriteInstr regA (DirAddr 7),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Load (ImmValue 1) regA,
    Store regA (DirAddr 1),
    Jump (Rel 1),
    Load (ImmValue 0) regA,
    WriteInstr regA (DirAddr 0),
    Jump (Rel  (-38)),
    WriteInstr regSprID (DirAddr 6),
    Jump (Abs 14),
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
    ReadInstr (DirAddr 4),
    Receive regA,
    Load (ImmValue 4) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-19)),
    ReadInstr (DirAddr 5),
    Receive regA,
    Load (ImmValue 5) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-24)),
    ReadInstr (DirAddr 6),
    Receive regA,
    Load (ImmValue 6) regC,
    Compute NEq regA regC regB,
    Branch regB (Rel (-29)),
    ReadInstr (DirAddr 7),
    Receive regA,
    WriteInstr regA numberIO,
    Compute Add reg0 regPC regA,
    Compute Equal regSprID reg0 regB,
    Branch regB (Rel 2),
    EndProg,
    WriteInstr regA (DirAddr 1),
    WriteInstr regA (DirAddr 2),
    WriteInstr regA (DirAddr 3),
    WriteInstr regA (DirAddr 4),
    WriteInstr regA (DirAddr 5),
    WriteInstr regA (DirAddr 6),
    EndProg]
main = run [prog,prog,prog,prog,prog,prog,prog]
