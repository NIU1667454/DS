import 'package:flutter/material.dart';

import 'data.dart';

//PANTALLA DE LES ACCIONS -> ACABADA

class ScreenListActions extends StatefulWidget {
  late UserGroup userGroup;

  ScreenListActions({super.key, required this.userGroup});

  @override
  State<ScreenListActions> createState() => _ScreenListActionsState();
}

class _ScreenListActionsState extends State<ScreenListActions> {
  late List<bool> isChecked = [true, true, false, false, false]; 
  @override
  void initState() {
    super.initState();
    for(int i = 0; i < widget.userGroup.actions.length; i++) {
      isChecked[i] = widget.userGroup.checked[i];
    }
   }

List<Widget> _buildActionList() {
  List<Widget> widgets = [];
  for (int i = 0; i < widget.userGroup.actions.length; i++) {
    widgets.add(
      CheckboxListTile(
        title: Text(widget.userGroup.actions[i], style: TextStyle(fontSize: 25)),
        subtitle: Text(widget.userGroup.areasDescription[i], style: TextStyle(fontSize: 20)),
        value: isChecked[i], // Estado actual
        onChanged: (bool? newValue) {
          setState(() {
            isChecked[i] = newValue ?? false; // Actualizar estado
          });
        },
      ),
    );
    widgets.add(Divider());
  }
  widgets.add(
    Padding(
          padding: const EdgeInsets.all(18.0),
          child: ElevatedButton(
            child: Text('Submit'),
            onPressed: () {
              ScaffoldMessenger.of(context).showSnackBar(
                    SnackBar(content: Text('Saved')),
              );
              for(int i = 0; i < widget.userGroup.actions.length; i++) {
                widget.userGroup.checked[i] = isChecked[i];
              }
            },
          ),
        ),
  );
  return widgets;
}

@override
Widget build(BuildContext context) {
  return Scaffold(
    appBar: AppBar(
      backgroundColor: Theme.of(context).colorScheme.primary,
      foregroundColor: Theme.of(context).colorScheme.onPrimary,
      title: const Text("Actions"),
    ),
    body: Column(
      children: _buildActionList(),
    ),
  );
}

}

