# Android-Permissions-Auto-Splash
Android Very Easy Permissions Request All Auto Load  First Activity  Or Need Permission Request

안드로이드 퍼미션을 미니페스트에서 읽어서 첫 액티비티에서 자동으로 처리해주는 라이브러리입니다.
자동 처리이외에도 상황별 퍼미션처리 가 쉽게 가능합니다.

Auto code


        Permissions permission;
   

        Permissions.Build build=  new Permissions.Build();
        Permissions.permissionsCallback callback=new Permissions.permissionsCallback() {
            @Override
            public void onRequestPermissionsResult_GRANTED() {
                /* All Permission Granted
                */
            }

            @Override
            public void onRequestPermissionsResult_DENIED() {
             /*
              force default nothing   you use setForceFlag(false)
             */
            }
        };
        permission=build.setContext(Splash.this).setAllFlag(true).setMessage("모든권한을 허용하여야 앱이실행됩니다.").setCallback(callback).setForceFlag(true).build();
        permission.checkPermissions(100);

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
                                           
        permission.onRequestPermissionsResult(requestCode,permissions,grantResults);


    }
    


use need Request Permission very simple 


        {
        Permissions.Build build=  new Permissions.Build();
        Permissions.permissionsCallback callback=new Permissions.permissionsCallback() {
            @Override
            public void onRequestPermissionsResult_GRANTED() {
                /*  Permission Granted
                 Permissions.getAlert(context,"승인").show();
                */
            }

            @Override
            public void onRequestPermissionsResult_DENIED() {
             /*
                    Permissions.getAlert(context,"거부").show();
             */
            }
        };
        permission=build.setContext(Splash.this).setAllFlag(false).setCallback(callback).setsetForceFlag(false).
          setMypermission(new String[]{Manifest.permission.CALL_PHONE}).build();
        
        Button delbtn=(Button)findViewById(R.id.delbtn);
        delbtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Check Permissions
                 permission.checkPermissions(200);
            }
        });
     
        }
        }
         @Override
         public void onRequestPermissionsResult(int requestCode,  String permissions[], int[] grantResults) {
           
             if(requestCode==200){
                  permission.onRequestPermissionsResult(requestCode,permissions,grantResults);
             }
          }
    
    
        

   
