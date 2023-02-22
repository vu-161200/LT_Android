package com.example.filemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    final int REQUEST = 112;

    File currentPath;
    File currentDirectoryPath;
    List<ItemModel> items;
    GridView listFiles;
    ActionBar actionBar;

    Context _context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init
        listFiles = findViewById(R.id.main);
        items = new ArrayList<>();

        // Calling the action bar
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
        }

        // Request READ, WRITE Permission
        String[] PERMISSIONS = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, REQUEST );
        }else{
            init();
        }
    }

    void init(){
//         Assign currentPath
//         0 - Internal path, 1 - External path
//        currentPath = getExternalFilesDirs(null)[1];
//
//        // Don't have SD card
//        if(currentPath == null) return;
//
//        // Get root path of sdcard
//        while(!currentPath.getParentFile().getAbsolutePath().equals("/storage")) {
//            currentPath = currentPath.getParentFile();
//        }

        // Assign currentPath
        currentPath = Environment.getExternalStorageDirectory();

        initializeItems();

        // Register Context Menu <=> Hold item on listview
        registerForContextMenu(listFiles);

        // Click Event ==> Open foler / file
        listFiles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File fileSelectedPath = items.get(i).getPath();

                if(fileSelectedPath.isDirectory()){
                    currentPath = fileSelectedPath;
                    initializeItems();

                    actionBar.setDisplayHomeAsUpEnabled(true);
                }else{
                    showItemDialog(fileSelectedPath);
                }
            }
        });
    }

    public void initializeItems() {
            Log.e("CURRENT PATH", currentPath.getAbsolutePath());
            items.clear();

            File[] _files = currentPath.listFiles();
            if (_files != null) {
                for(File _file : _files) {
                    items.add(new ItemModel(_file.getName(), getIconBasedOnFileType(_file), _file));
                }
            }

            // Add adapter
            ItemAdapter adapter = new ItemAdapter(items, this);
            listFiles.setAdapter(adapter);
    }

    String getIconBasedOnFileType(File file) {
        String icon = "";

        if(file.isDirectory()) icon = "directory";
        else if(file.getName().toLowerCase().endsWith(".txt")) icon = "text";
        else if(file.getName().toLowerCase().endsWith(".bmp") || file.getName().toLowerCase().endsWith(".jpg") || file.getName().toLowerCase().endsWith(".png")) icon = "image";
        else icon = "unknown";

        return icon;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    Toast.makeText(this, "The app was not allowed to read your store.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            // Arrow back
            case android.R.id.home:
                currentPath = currentPath.getParentFile();
                if(Objects.requireNonNull(currentPath).getName().equals("0")) actionBar.setDisplayHomeAsUpEnabled(false);

                initializeItems();

                return true;
            case R.id.new_file:
                showInputDialog("Create new file", "Enter file name", currentPath, "Create File");
                return true;
            case R.id.new_folder:
                showInputDialog("Create new folder", "Enter folder name", currentPath, "Create Directory");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

        menu.setHeaderTitle("Choose type: ");
        getMenuInflater().inflate(R.menu.menu_file, menu);

        if(items.get(info.position).getPath().isDirectory()) menu.getItem(menu.size() - 1).setVisible(false);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        String type = items.get(info.position).getPath().isDirectory() ? "Directory" : "File";

        switch (item.getItemId()){
            case R.id.delete:
                showAlertDialog(this, items.get(info.position).getPath(), type);
                return true;
            case R.id.rename:
                showInputDialog("Rename", "Enter new name ...", items.get(info.position).getPath(), "Rename " + type);
                return true;
            case R.id.copy_to:
                showCopyFileDialog(items.get(info.position).getPath());
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void showItemDialog(File path) {
        final Dialog customDialog = new Dialog(this);

        // match customDialog with custom dialog layout
        customDialog.setContentView(R.layout.layout_item_dialog);

        // File Name
        ((TextView) customDialog.findViewById(R.id.file_name)).setText(path.getName());

        TextView txt = customDialog.findViewById(R.id.file_txt);
        ImageView img = customDialog.findViewById(R.id.file_image);

        if(path.getName().endsWith(".txt")){
            img.setVisibility(View.GONE);

            try {
                BufferedReader myReader = new BufferedReader(new InputStreamReader(new FileInputStream(path)));

                String aDataRow = "";
                StringBuilder aBuffer = new StringBuilder();
                while ((aDataRow = myReader.readLine()) != null) {
                    aBuffer.append(aDataRow).append("\n");
                }

                myReader.close();

                txt.setText(aBuffer.toString());
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }else{
            txt.setVisibility(View.GONE);

            Bitmap bitmap = BitmapFactory.decodeFile(path.getAbsolutePath());
            img.setImageBitmap(bitmap);
        }

        // OK
        ((Button) customDialog.findViewById(R.id.file_btnOK)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }

    private void showCopyFileDialog(File path) {
        final Dialog customDialog = new Dialog(this);

        // match customDialog with custom dialog layout
        customDialog.setContentView(R.layout.layout_copy_file_dialog);

        // Path
        TextView pathHere = (TextView) customDialog.findViewById(R.id.copy_file_dialog_path);
        pathHere.setText(path.getParentFile().getAbsolutePath());

        _context = this;
        GridView _list = (GridView) customDialog.findViewById(R.id.copy_file_dialog_list);
        currentDirectoryPath = path.getParentFile();
        List<ItemModel> _directories = new ArrayList<>();

        File[] _files = currentDirectoryPath.listFiles();
        if (_files != null) {
            for(File _file : _files) {
                if(_file.isDirectory()) _directories.add(new ItemModel(_file.getName(), "directory", _file));
            }
        }

        // Add adapter
        ItemAdapter _adapter = new ItemAdapter(_directories, _context);
        _list.setAdapter(_adapter);

        _list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File _fileSelectedPath = _directories.get(i).getPath();

                if(_fileSelectedPath.isDirectory()){
                    currentDirectoryPath = _fileSelectedPath;

                    File[] _files = currentDirectoryPath.listFiles();
                    if (_files != null) {
                        _directories.clear();
                        for(File _file : _files) {
                            if(_file.isDirectory()) _directories.add(new ItemModel(_file.getName(), "directory", _file));
                        }
                        // Add adapter
                        ItemAdapter _adapter = new ItemAdapter(_directories, _context);
                        _list.setAdapter(_adapter);

                        pathHere.setText(currentDirectoryPath.getAbsolutePath());
                    }

                }
            }
        });

        pathHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentDirectoryPath.getName().equals("0")) return;

                currentDirectoryPath = currentDirectoryPath.getParentFile();

                File[] _files = currentDirectoryPath.listFiles();
                if (_files != null) {
                    _directories.clear();
                    for(File _file : _files) {
                        if(_file.isDirectory()) _directories.add(new ItemModel(_file.getName(), "directory", _file));
                    }
                    // Add adapter
                    ItemAdapter _adapter = new ItemAdapter(_directories, _context);
                    _list.setAdapter(_adapter);

                    pathHere.setText(currentDirectoryPath.getAbsolutePath());
                }
            }
        });

        // OK
        ((Button) customDialog.findViewById(R.id.copy_file_dialog_btnOK)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(copyFile(path, new File(currentDirectoryPath.getAbsolutePath(), path.getName()))){
                    initializeItems();
                    Toast.makeText(getApplicationContext(), "File has been copied", Toast.LENGTH_SHORT).show();
                }

                customDialog.dismiss();
            }
        });

        // Cancel
        ((Button) customDialog.findViewById(R.id.copy_file_dialog_btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        customDialog.show();
    }

    private void showInputDialog(String title, String hint, File path, String type) {
        final Dialog customDialog = new Dialog(this);

        // match customDialog with custom dialog layout
        customDialog.setContentView(R.layout.layout_input_dialog);

        // Title
        ((TextView) customDialog.findViewById(R.id.cd_title)).setText(title);

        // Hint
        final EditText fileName = (EditText) customDialog.findViewById(R.id.cd_name);
        fileName.setHint(hint);

        // Close
        ((Button) customDialog.findViewById(R.id.cd_btnClose)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });

        // OK
        ((Button) customDialog.findViewById(R.id.cd_btnOK)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fileName.getText().toString();

                if(!name.equals("")){
                    try {
                        if(type.equals("Create File")){
                            String fileName = name.endsWith(".txt") ? name : name + ".txt";

                            File file = new File(path.getAbsolutePath(), fileName);
                            if(file.createNewFile()){
                                initializeItems();
                                Toast.makeText(getApplicationContext(), "File '" + fileName + "' has been created", Toast.LENGTH_LONG).show();
                            }

                        }
                        else if(type.equals("Create Directory")){
                            File directory = new File(path.getAbsolutePath(), name);
                            if(directory.mkdirs()){
                                initializeItems();
                                Toast.makeText(getApplicationContext(), "Directory '" + name + "' has been created", Toast.LENGTH_LONG).show();
                            }

                        }
                        else if(type.equals("Rename File")){
                            String newName = name.endsWith(".txt") ? name : name + ".txt";

                            File targetFile = new File(path.getParentFile().getAbsolutePath(), newName);
                            if(path.renameTo(targetFile)){
                                initializeItems();
                                Toast.makeText(getApplicationContext(), "File has been renamed!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else if(type.equals("Rename Directory")){
                            File targetDirectory = new File(path.getParentFile().getAbsolutePath(), name);
                            if(path.renameTo(targetDirectory)){
                                initializeItems();
                                Toast.makeText(getApplicationContext(), "Directory has been renamed!", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                customDialog.dismiss();
            }
        });

        customDialog.show();
    }

    private void showAlertDialog(MainActivity mainActivity, File path, String type) {
        new AlertDialog.Builder(mainActivity)
                .setTitle("Delete " + type.toLowerCase())
                .setMessage("Are you sure that you want to delete " + type.toLowerCase() + "?")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) { }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        try {
                            if(type.equals("File")){
                                if(path.delete()){
                                    initializeItems();
                                    Toast.makeText(getApplicationContext(), "File has been deleted", Toast.LENGTH_LONG).show();
                                }

                            }else if(type.equals("Directory")){
                                if(deleteDirectory(path)){
                                    initializeItems();
                                    Toast.makeText(getApplicationContext(), "Directory has been deleted", Toast.LENGTH_LONG).show();
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_LONG).show();
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                })
                .create()
                .show();
    }// showMyAlertDialog

    public boolean deleteDirectory(File path) {
        if(path.exists()) {
            File[] files = path.listFiles();

            if (files == null) {
                return true;
            }

            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    if(!file.delete()) return false;
                }
            }
        }
        return path.delete();
    }

    public boolean copyFile(File src, File dst) {
        try (InputStream in = new FileInputStream(src)) {
            try (OutputStream out = new FileOutputStream(dst)) {
                // Transfer bytes from in to out
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } catch (IOException e) {
                Log.e("ERROR", e.getMessage());
                return false;
            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
            return false;
        }

        return true;
    }
}