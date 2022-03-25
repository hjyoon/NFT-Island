import {
	Avatar,
	Box,
	Button,
	Stack,
	Container,
	Typography,
	Grid,
	TextField,
	Paper,
	InputBase,
	Divider,
	IconButton,
} from '@mui/material';

import {
	Person as PersonIcon,
	Search as SearchIcon,
	Add as AddIcon,
} from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';
import Page from '../components/Page';
import PostCard from '../layouts/community-detail/PostCard';
import logo from '../image/logo.png';

function CommunityDetail() {
	const navigate = useNavigate();

	const onClickButton = () => {
		navigate('/postwrite');
	};

	return (
		<Page>
			<Container>
				<Box>
					<Typography variant='h3' align='center'>
						{/* 커뮤니티 이름 */}리사모
					</Typography>
					<Typography position='absolute' right='10%' mt='-50px'>
						{/* 커뮤니티 뱃지수..? */}341/513
					</Typography>
				</Box>
				<Box ml='10%' mr='10%' mt='3%' mb='3%'>
					<Stack direction='row'>
						<Stack flex='1'>
							<Stack direction='row'>
								<Avatar sx={{ width: 35, height: 35 }}>
									<PersonIcon />
									{/* 커뮤니티장 아이콘 */}
								</Avatar>
								<Typography ml='10px' mt='5px'>
									hhhhhdong{/* 커뮤니티장 닉네임 */}
								</Typography>
							</Stack>
							<img src={logo} alt='' width='90%' />
							{/* 커뮤니티 이미지 */}
						</Stack>
						<Stack flex='2'>
							<Typography fontSize='30px' mb='15px'>
								<b>Get Popular NFT</b>
								{/* 커뮤니티 대표 설명 */}
							</Typography>
							<Typography>
								Get Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get
								Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular
								NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get
								Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular
								NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get
								Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular
								NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get
								Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular
								NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get
								Popular NFT Get Popular NFT Get Popular NFT Get Popular NFT Get Popular
								NFT
								{/* 커뮤니티 설명 */}
							</Typography>
						</Stack>
					</Stack>
				</Box>
				<Box mt='5%' mb='3%'>
					<Stack direction='row'>
						<Paper
							component='form'
							variant='outlined'
							sx={{
								mr: '2%',
								p: '2px 4px',
								display: 'flex',
								alignItems: 'center',
								width: '100%',
							}}
						>
							<InputBase
								sx={{ ml: 1, flex: 1 }}
								placeholder=''
								inputProps={{ 'aria-label': 'search google maps' }}
							/>
							<Divider sx={{ height: 28, m: 0.5 }} orientation='vertical' />
							<IconButton type='submit' sx={{ p: '10px' }} aria-label='search'>
								<SearchIcon />
							</IconButton>
						</Paper>
						<Button
							sx={{ width: '30%' }}
							variant='contained'
							disableElevation
							endIcon={<AddIcon />}
							onClick={onClickButton}
						>
							게시글 생성
						</Button>
					</Stack>
				</Box>
				<Box maxWidth='xl' sx={{ pt: '30px' }}>
					<Grid container spacing={6}>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
						<Grid sx={{ mb: 5 }} item xs={12} sm={6} md={4} lg={3}>
							<PostCard />
						</Grid>
					</Grid>
				</Box>
			</Container>
		</Page>
	);
}

export default CommunityDetail;