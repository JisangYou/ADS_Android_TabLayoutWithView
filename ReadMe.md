# ADS04 Android

## 수업 내용

- View와 ViewPager를 사용해 TabLayout을 만드는 학습

## Code Review

### MainActivity

```Java
public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTabLayout();
        setViewPager();

        setListener();
    }

    private void setListener(){
        // 탭레이아웃을 ViewPager와 연결
        tabLayout.addOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager)
        );
        // ViewPager의 변경사항을 탭레이아웃에 전달
        viewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(tabLayout)
        );
    }

    private void setTabLayout(){
        // 탭 레이아웃 생성
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab( tabLayout.newTab().setText("One") );
        tabLayout.addTab( tabLayout.newTab().setText("Two") );
        tabLayout.addTab( tabLayout.newTab().setText("Three") );
        tabLayout.addTab( tabLayout.newTab().setText("Four") );

    }

    private void setViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        CustomAdapter adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);
    }
}
```

### CustomAdapter

``` Java
public class CustomAdapter extends PagerAdapter {
    private static final int COUNT = 4;
    List<View> views;

    public CustomAdapter(Context context) {

        views = new ArrayList<>();
        views.add(new One(context));
        views.add(new Two(context));
        views.add(new Three(context));
        views.add(new Four(context));

    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
//        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object; /
    }
}
```

### One

```Java
public class One extends FrameLayout{ 

    public One(Context context) {
        super(context);
        initView(); // One class를 호출할때, 자체적으로 view가 add됨.
    }

    public One(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 여기서 내가 만든 레이아웃을 inflate하고
    // 나 자신에게 add한다.

    private void initView(){
        // 1. 레이아웃 파일로 뷰를 만들고
        View view =LayoutInflater.from(getContext()).inflate(R.layout.fragment_one, null);

        // 로직작성

        addView(view);
    }
}
```

### Two

``` Java
public class Two extends FrameLayout{

    public Two(Context context) {
        super(context);
        initView();
    }

    public Two(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    // 여기서 내가 만든 레이아웃을 inflate하고
    // 나 자신에게 add한다.

    private void initView(){
        // 1. 레이아웃 파일로 뷰를 만들고
        View view =LayoutInflater.from(getContext()).inflate(R.layout.fragment_two, null);

        addView(view);
    }
}
```

- Three와 Four도 같으므로, 생략


## 보충설명

- public class One extends FrameLayout 으로 뷰를 상속받아 커스터마이징 할 수 있음.
- 아래와 같이 해당 layout을 inflate해준 것을 view객체에 담아, 동적으로 추가해줄 수 있다. 
```Java
View view =LayoutInflater.from(getContext()).inflate(R.layout.fragment_two, null);
    addView(view);
```
- mainActivity에서 다음과 같이 viewpager 및 adapter를 선언해주면,
```Java
      viewPager = (ViewPager) findViewById(R.id.viewPager);

        CustomAdapter adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);
```
- CustomAdapter에 context가 넘어오면서, views List에 뷰들이 더해진다.
```Java
 List<View> views;

    public CustomAdapter(Context context) {

        views = new ArrayList<>();
        views.add(new One(context));
        views.add(new Two(context));
        views.add(new Three(context));
        views.add(new Four(context));

    }
```

### 출처

- null

## TODO

- 코드에 대한 흐름 이해 필요.
- tablayout을 활용한 화면 구성에서 View와 Fragment 두개 다 사용할 수 있음. 즉, 프로그램을 만드는데 정답은 없으므로, 기능들을 사용하면서 생기는 로직 및 성능 등의 차이등을 생각하면서 만들어 볼것 
- 선택한 기술에 대한 근거


## Retrospect

- 동적으로 뷰를 생성하는 것, 뷰를 상속받는 다는 것의 의미, 다양한 adapter종류 등 이해가 가지만, 막상 코딩을 하려면 생각하는 시간이 걸림. 

## Output

- Fragment로 만든 TabLayout과 결과물 동일