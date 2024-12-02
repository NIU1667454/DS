import 'package:flutter/material.dart';

import 'data.dart';

//PANTALLA INFO - ACABADA

class ScreenInfo extends StatefulWidget {
  UserGroup userGroup;

  ScreenInfo({super.key, required this.userGroup});

  @override
  State<ScreenInfo> createState() => _ScreenInfoState();
}

class _ScreenInfoState extends State<ScreenInfo> {

  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final TextEditingController nameController = TextEditingController();
    final TextEditingController descriptionController = TextEditingController(text: 'Info dels admins');
    nameController.text = widget.userGroup.name;
    descriptionController.text = widget.userGroup.description;
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text('Info ${widget.userGroup.name}'),
      ),
      body: Center(
        child: GridView.count(
          crossAxisCount: 1,
          padding: const EdgeInsets.all(16.0),
          children: [
            Container(
              child: Column(
                children: [
                  TextField(
                    controller: nameController,
                    decoration: InputDecoration(
                      labelText: 'Name Group',
                      border: OutlineInputBorder(),
                    ),
                  ),
                  SizedBox(height: 8),
                  TextField(
                    controller: descriptionController,
                    decoration: InputDecoration(
                      labelText: 'Description',
                      border: OutlineInputBorder(),
                    ),
                    maxLines: 3,
                  ),
                  Padding(
                    padding: const EdgeInsets.all(16.0),
                    child: SizedBox(
                            width: 150,
                            height: 40,
                          child: ElevatedButton(
                            onPressed: () {
                              ScaffoldMessenger.of(context).showSnackBar(
                                SnackBar(content: Text('Saved')),
                              );
                              widget.userGroup.name = nameController.text;
                              widget.userGroup.description = descriptionController.text;
                              setState(() { });
                            },
                            child: Text('Submit'),
                          ),
                          ), 
                  )
                  
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}