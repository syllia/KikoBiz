package bj.kiko.projects.kikobiz.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import bj.kiko.projects.kikobiz.R;


public class PosterFragment extends Fragment {

    private Button mSelectImageBtn;
    private Button mTakePhotoBtn;
    private int SELECT_IMAGE_INTENT=1234;
    private int TAKE_PHOTO_INTENT=999;
    private View view;
    private String mSelectedImagePath;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getActivity().getResources().getString(R.string.FragmentPost));
        view = inflater.inflate(R.layout.fragment_poster, container, false);

        mTakePhotoBtn = (Button)view.findViewById(R.id.addPhotobutton);
        mTakePhotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent cameraIntent = new Intent(
                        android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, TAKE_PHOTO_INTENT);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_IMAGE_INTENT && resultCode==Activity.RESULT_OK){
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            Cursor cursor = getActivity().getContentResolver().query(selectedImage,filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            mSelectedImagePath=picturePath;
            ImageView imageView = (ImageView) view.findViewById(R.id.first);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
        }else if(requestCode==TAKE_PHOTO_INTENT && resultCode== Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageView imageView = (ImageView) view.findViewById(R.id.first);
            imageView.setImageBitmap(photo);
            //we
        }
    }
}
