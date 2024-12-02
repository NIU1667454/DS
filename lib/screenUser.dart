import 'package:flutter/material.dart';

import 'data.dart';

// PANTALLA PER MODIFICAR LA INFO DELS USUARIS

class ScreenUser extends StatefulWidget {
  User user;

  ScreenUser({super.key, required this.user});

  @override
  State<ScreenUser> createState() => _ScreenUserState();
}

class _ScreenUserState extends State<ScreenUser> {
  @override
  void initState() {
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    final TextEditingController nameController = TextEditingController();
    final TextEditingController credentialController = TextEditingController();
    nameController.text = widget.user.name;
    credentialController.text = widget.user.credential;
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.primary,
        foregroundColor: Theme.of(context).colorScheme.onPrimary,
        title: Text('User'),
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
                      labelText: 'Name',
                      border: OutlineInputBorder(),
                    ),
                    maxLines: 1,
                  ),
                  SizedBox(height: 8),
                  TextField(
                    controller: credentialController,
                    decoration: InputDecoration(
                      labelText: 'Credential',
                      border: OutlineInputBorder(),
                    ),
                    maxLines: 1,
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
                              widget.user.name = nameController.text;
                              widget.user.credential = credentialController.text;
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