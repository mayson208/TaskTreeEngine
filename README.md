📘 TaskTreeEngine

A visual task mind-map engine built with JavaFX.
Think of it as a mix between a to-do list, a creative canvas, and a node-based editor.
The goal is to make organizing tasks fun instead of boring.

🌳 What Is TaskTreeEngine?

TaskTreeEngine lets you create visual “nodes” that represent tasks and connect them in a branching, mind-map style layout.
You can drag them, resize them, link ideas together, and eventually customize the entire theme of the workspace.

This project is being developed as a polished, long-term JavaFX application with a focus on:

smooth visuals

customizable UI

clean architecture

expandability for future features

It’s meant to feel like a modern creative tool rather than a plain desktop app.

✨ Current Features
🟦 Node System

Create nodes by clicking anywhere

Drag nodes around

Resize nodes using a draggable handle

Double-click to rename

Right-click context menu (rename, delete, recolor, add child)

Smooth animations (pop-in, highlight, pulse)

🟩 Edges & Connections

Click → click to create curved connections

Live preview curve while connecting

Automatic updates when nodes move

Supports hierarchical “parent → child” task structures

🟧 Canvas & Interaction

Pan with middle mouse drag

Zoom in/out with scroll

Clean coordinate transforms

Optional snapping/grid (coming soon)

🟪 Core Architecture

Organized project structure

Utility classes for geometry, animations, layout, themes

Theme system with:

Light mode

Dark mode

Blueprint mode

Layout utils (tree layout, radial layout, force-directed)

🎮 Controllers

DragController: Canvas movement

InputController: keyboard shortcuts (theme switch, delete, future undo/redo)

NodeController: node interaction and selection

🚧 Planned Features (Coming Soon)
🎨 UI & Theme Expansion

Full theme customization

CSS-driven editor

Node color palettes

Blueprint-mode grid background

🗺️ Navigation Tools

Minimap / overview map

Auto-focus on selected node

Smart camera movement

🔄 Undo / Redo System

Full action history

Reversible node creation, deletion, movement, rename, and connections

📐 Auto Layout

Smart tree layout

Radial mind-map layout

Force-directed graph layout

One-click “organize workspace” button

🧩 Extra Node Features

Tags, priority, metadata

Attachments or notes on nodes

Custom icons

📤 Exporting

Export as PNG

Export as SVG

Save/load project state


🤝 Contributors

- **Mason** — Lead Developer  
- **Nat** — Co-Developer (GitHub link coming soon)

📝 Notes

This project is still early-stage, but the foundation is intentionally strong so it can grow into a full creative productivity tool.
Expect visuals to get smoother, features to stack up, and customization options to expand a LOT over time.